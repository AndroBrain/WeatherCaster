package com.androbrain.weathercaster.ui.core

import androidx.annotation.StringRes
import com.androbrain.weathercaster.R
import com.androbrain.weathercaster.domain.core.ResultErrorType

@StringRes
fun ResultErrorType.getMessage() = when (this) {
    ResultErrorType.NETWORK -> R.string.result_err_network
    ResultErrorType.UNKNOWN -> R.string.result_err_unknown
    ResultErrorType.NOT_FOUND -> R.string.result_err_not_found
    ResultErrorType.TOO_MANY_REQUESTS -> R.string.result_err_too_many_requests
    ResultErrorType.SERVER -> R.string.result_err_server
    ResultErrorType.WRONG -> R.string.result_err_wrong
}
