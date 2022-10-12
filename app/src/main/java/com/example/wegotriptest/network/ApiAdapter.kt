package com.example.wegotriptest.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val USER_DATA_URL = "https://app.wegotrip.com/"
private const val BASE_URL = "https://webhook.site/"

@OptIn(ExperimentalSerializationApi::class)
object ApiAdapter {

    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }
    private val okClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    fun init(isNeedAvatar: Boolean = false): Retrofit {
        return Retrofit.Builder()
            .baseUrl(if (isNeedAvatar) USER_DATA_URL else BASE_URL)
            .client(okClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

}