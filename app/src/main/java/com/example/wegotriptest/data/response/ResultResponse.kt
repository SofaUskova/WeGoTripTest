package com.example.wegotriptest.data.response

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val isSuccess: Boolean
)