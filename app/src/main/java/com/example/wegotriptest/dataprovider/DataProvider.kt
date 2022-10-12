package com.example.wegotriptest.dataprovider

import com.example.wegotriptest.data.request.FeedbackRequest
import com.example.wegotriptest.data.response.PersonalData
import com.example.wegotriptest.data.response.ResultResponse
import com.example.wegotriptest.network.ApiAdapter
import com.example.wegotriptest.network.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DataProvider {

    private val apiAvatar: ApiClient = ApiAdapter.init(true).create(ApiClient::class.java)
    private val api: ApiClient = ApiAdapter.init().create(ApiClient::class.java)

    fun getUser(): Flow<Response<PersonalData>> {
        return flow {
            val result = apiAvatar.getUser()
            emit(result)
        }
    }

    fun setFeedback(feedback: FeedbackRequest): Flow<Response<ResultResponse>> {
        return flow {
            val result = api.setFeedback(feedback)
            emit(result)
        }
    }

}