/*
 * Copyright © 2026 Oksana Shumilova.
 * All rights reserved.
 *
 * This source code is provided for portfolio and evaluation purposes only.
 * Unauthorized copying, modification, or distribution is prohibited.
 */

package shum.oks.lab.core.network

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failure<T>(val exception: ApiException) : ApiResult<T>()
}
