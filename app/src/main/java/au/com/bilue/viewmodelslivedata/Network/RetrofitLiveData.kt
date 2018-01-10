package au.com.bilue.viewmodelslivedata.Network

import android.arch.lifecycle.LiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by simonwestren on 10/1/18.
 */

class RetrofitLiveData<T>(private val call: Call<T>) : LiveData<T>(), Callback<T> {

	override fun onActive() {
		Log.i("onActive", "Starting call")
		if (!call.isCanceled && !call.isExecuted) call.enqueue(this)
	}

	override fun onFailure(call: Call<T>?, t: Throwable?) {
		//not implemented
		Log.i("Failed: ", "call: " + call + ", t: " + t)
	}

	override fun onResponse(call: Call<T>?, response: Response<T>?) {
		Log.i("Success", "response: " + response)
		value = response?.body()
	}

	fun refresh() {
		call.enqueue(this)
	}

	fun cancel() = if(!call.isCanceled) call.cancel() else Unit
}