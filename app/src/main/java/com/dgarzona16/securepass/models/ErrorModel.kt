package com.dgarzona16.securepass.models

import com.dgarzona16.securepass.enums.ErrorType

data class ErrorModel(
    val title: String,
    val message: String,
    val btnText: String,
    val errorType: ErrorType
)
