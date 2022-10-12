package com.example.wegotriptest.data.response

import kotlinx.serialization.Serializable

@Serializable
data class PersonalData(
    val data: Data
)

@Serializable
data class Data(
    val author: Author,
)

@Serializable
data class Author(
    val avatar: String,
    val name: String,
)