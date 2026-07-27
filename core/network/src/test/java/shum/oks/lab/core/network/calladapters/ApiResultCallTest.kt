/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network.calladapters

import kotlinx.coroutines.runBlocking
import net.jqwik.api.Arbitraries
import net.jqwik.api.Arbitrary
import net.jqwik.api.ForAll
import net.jqwik.api.Property
import net.jqwik.api.Provide
import net.jqwik.api.lifecycle.AfterProperty
import net.jqwik.api.lifecycle.BeforeProperty
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import shum.oks.lab.core.network.ApiClientException
import shum.oks.lab.core.network.ApiNetworkException
import shum.oks.lab.core.network.ApiResult
import shum.oks.lab.core.network.ApiServerException
import shum.oks.lab.core.network.ApiUnknownException

internal class ApiResultCallTest {

    private lateinit var server: MockWebServer
    private lateinit var service: TestService

    interface TestService {
        @GET(TEST_PATH)
        suspend fun getData(): ApiResult<String>
    }

    @BeforeEach
    @BeforeProperty
    fun before() {
        server = MockWebServer()
        service = buildService(server.url(TEST_PATH))
    }

    @AfterEach
    @AfterProperty
    fun after() {
        server.shutdown()
    }

    private fun buildService(baseUrl: okhttp3.HttpUrl): TestService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(ApiResultCallAdapterFactory())
            .build()
            .create(TestService::class.java)

    @Test
    fun `when response is OK, should return Success`() = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(SUCCESS_RESPONSE_CODE)
                .setBody(SUCCESS_BODY_MESSAGE)
        )

        val result = service.getData()
        assertTrue(result is ApiResult.Success)
        assertEquals(SUCCESS_BODY_MESSAGE, (result as ApiResult.Success).data)
    }

    @Provide
    fun okCodes(): Arbitrary<Int> = Arbitraries.of(204, 205)
    @Property
    fun `should map any response body is null (No Content) to Failure with UnknownException`(
        @ForAll("okCodes") okCode: Int
    ) = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(okCode)
        )
        val result = service.getData()
        assertTrue(result is ApiResult.Failure)
        assertTrue((result as ApiResult.Failure).exception is ApiUnknownException)
    }

    @Provide
    fun clientErrorCodes(): Arbitrary<ErrorCase> =
        Arbitraries.of(
            ErrorCase(400, "Bad Request", ApiClientException.Code.BadRequest),
            ErrorCase(401, "Unauthorized", ApiClientException.Code.Unauthorized),
            ErrorCase(403, "Forbidden", ApiClientException.Code.Forbidden),
            ErrorCase(404, "Not Found", ApiClientException.Code.NotFound),
            ErrorCase(409, "Conflict", ApiClientException.Code.Conflict),
            ErrorCase(429, "Too Many Requests", ApiClientException.Code.TooManyRequests),
            ErrorCase(402, "Payment Required", ApiClientException.Code.Unknown),
        )
    @Property
    fun `should map any 4xx error code to ApiClientException`(
        @ForAll("clientErrorCodes") errorCase: ErrorCase
    ) = runBlocking {
        server.enqueue(
            MockResponse()
                .setResponseCode(errorCase.code)
                .setBody(errorCase.bodyMessage)
        )

        val result = service.getData()
        assertTrue(result is ApiResult.Failure)

        val exception = (result as ApiResult.Failure).exception
        assertTrue(exception is ApiClientException)
        assertEquals(errorCase.apiClientExceptionCode, (exception as ApiClientException).code)
    }

    @Provide
    fun serverErrorCodes(): Arbitrary<Int> = Arbitraries.integers().between(500, 599)
    @Property
    fun `should map any 5xx error code to ApiServerException`(
        @ForAll("serverErrorCodes") code: Int
    ) = runBlocking {
        server.enqueue(MockResponse().setResponseCode(code).setBody(SERVER_ERROR_BODY_MESSAGE))

        val result = service.getData()
        assertTrue(result is ApiResult.Failure)
        assertTrue((result as ApiResult.Failure).exception is ApiServerException)
    }

    @Test
    fun `when server is unreachable, should return Failure with NetworkException`() = runBlocking {
        val unreachableServer = MockWebServer().apply { start() }
        val url = unreachableServer.url(TEST_PATH)
        unreachableServer.shutdown()
        val unreachableService = buildService(url)

        val result = unreachableService.getData()
        assertTrue(result is ApiResult.Failure)
        assertTrue((result as ApiResult.Failure).exception is ApiNetworkException)
    }

    companion object {
        private const val TEST_PATH = "/ololo/lalala/"
        private const val SUCCESS_RESPONSE_CODE = 200
        private const val SUCCESS_BODY_MESSAGE = "Success Body"
        private const val SERVER_ERROR_BODY_MESSAGE = "Server Error"
    }
}

data class ErrorCase(
    val code: Int,
    val bodyMessage: String,
    val apiClientExceptionCode: ApiClientException.Code
)
