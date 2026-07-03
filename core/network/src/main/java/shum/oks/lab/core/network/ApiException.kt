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

class ApiServerException(message: String) : ApiException(message)

class ApiUnknownException(
    message: String,
) : ApiException(message)
