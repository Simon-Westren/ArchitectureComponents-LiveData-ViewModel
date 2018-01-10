package au.com.bilue.viewmodelslivedata.Network

import au.com.bilue.viewmodelslivedata.Model.GitOrganisation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by simonwestren on 10/1/18.
 */

interface GitHubService {
	@GET("zen")
	fun findZen(): Call<String>
}

//class ZenHandler(val gitHubService: GitHubService) {
//	fun zen() : RetrofitLiveData<String> = RetrofitLiveData(gitHubService.findZen())
//}

//class RemoteDataSource private constructor() {
//
//	companion object Singleton {
//		val INSTANCE: RemoteDataSource by lazy { RemoteDataSource() }
//
//		const val BASE_URL = "https://api.github.com/"
//	}
//
//	val zenHandler: ZenHandler
//
//	init {
//
//		var retrofit = Retrofit.Builder()
//				.baseUrl("https://api.github.com/")
//				.addConverterFactory(GsonConverterFactory.create())
//				.build()
//
//		var service = retrofit.create<GitHubService>(GitHubService::class.java)
//		zenHandler = ZenHandler(service)
//	}
//}