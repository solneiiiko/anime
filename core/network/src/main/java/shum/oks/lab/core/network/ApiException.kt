/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network

import java.io.IOException

sealed class ApiException(
    message: String,
    throwable: Throwable? = null
) : Exception(message, throwable)

class ApiNetworkException(
    message: String,
    exception: IOException
) : ApiException(message, exception)

class ApiServerException(
    message: String
) : ApiException(message)

class ApiClientException(
    message: String,
    val code: Code,
) : ApiException(message) {

    sealed interface Code {
        object BadRequest : Code
        object Unauthorized : Code
        object Forbidden : Code
        object NotFound : Code
        object Conflict : Code
        object TooManyRequests : Code
        object Unknown : Code
    }
}

class ApiUnknownException(
    message: String,
    throwable: Throwable? = null,
) : ApiException(message, throwable)
