package com.example.wegotriptest.network

import com.example.wegotriptest.data.request.FeedbackRequest
import com.example.wegotriptest.data.response.PersonalData
import com.example.wegotriptest.data.response.ResultResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @GET("api/v2/products/3728/")
    suspend fun getUser(): Response<PersonalData>

    @POST("c8f2041c-c57e-433f-853f-1ef739702903/")
    suspend fun setFeedback(@Body feedback: FeedbackRequest): Response<ResultResponse>

}