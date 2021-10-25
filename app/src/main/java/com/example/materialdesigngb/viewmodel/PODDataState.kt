package com.example.materialdesigngb.viewmodel

import com.example.materialdesigngb.repository.PODServerResponseData

sealed class PODDataState{
    data class Success(val serverResponseData: PODServerResponseData) : PODDataState()
    data class Error(val error: Throwable) : PODDataState()
    data class Loading(val progress: Int?) : PODDataState()
}
