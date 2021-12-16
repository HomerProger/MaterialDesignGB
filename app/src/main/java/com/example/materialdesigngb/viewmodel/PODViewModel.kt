package com.example.materialdesigngb.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesigngb.BuildConfig
import com.example.materialdesigngb.repository.PODRetrofitImpl
import com.example.materialdesigngb.repository.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PODViewModel(
    private val liveDataToObserve: MutableLiveData<PODDataState> = MutableLiveData(),
    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()
) : ViewModel() {
    fun getLiveData(): LiveData<PODDataState> {
        return liveDataToObserve
    }

    fun sendServerRequest(){
        liveDataToObserve.postValue(PODDataState.Loading(null))
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isNotBlank()){
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(
                object: Callback<PODServerResponseData> {
                    override fun onResponse(
                        call: Call<PODServerResponseData>,
                        response: Response<PODServerResponseData>
                    ) {
                        if (response.isSuccessful&&response.body()!=null){
                            liveDataToObserve.postValue(PODDataState.Success(response.body()!!))

                        }
                        else{
                            // TODO
                        }
                    }

                    override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                        Log.d("myLogs", "onFailure: ошибка интернет соедниения")
                    }

                }
            )
        }
    }
}