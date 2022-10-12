package com.example.wegotriptest.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wegotriptest.data.request.toMapper
import com.example.wegotriptest.dataprovider.DataProvider
import com.example.wegotriptest.presentation.models.Feedback
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn

class BaseViewModel : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    val avatar = MutableLiveData<String?>()
    val errorMessage = MutableLiveData<String?>()

    fun getAvatarUserAvatar() {
        scope.launch {
            val result = kotlin.runCatching {
                DataProvider()
                    .getUser()
                    .flowOn(Dispatchers.Default)
                    .collect { response ->
                        if (response.isSuccessful) {
                            avatar.postValue(response.body()?.data?.author?.avatar)
                        } else {
                            errorMessage.postValue(
                                "Ошибка: ${response.message()} \n " +
                                        "Код ошибки: ${response.code()}"
                            )
                        }
                    }
            }
            if (result.isFailure) Log.e("EstimationViewModel", "getAvatar:isFailure")
        }
    }

    fun setFeedback(feedback: Feedback) {
        val feedbackRequest = toMapper(feedback)

        scope.launch {
            val result = kotlin.runCatching {
                DataProvider()
                    .setFeedback(feedbackRequest)
                    .flowOn(Dispatchers.Default)
                    .collect { response ->
                        if (!response.isSuccessful) {
                            errorMessage.postValue(
                                "Ошибка: ${response.message()} \n " +
                                        "Код ошибки: ${response.code()}"
                            )
                        }
                    }
            }

            if (result.isFailure) Log.e("EstimationViewModel", "setFeedback:isFailure")
        }
    }

}