package com.malik_isr.gifvk

class ApiException(
    val code: Int,
    message: String
) : Exception(message)
