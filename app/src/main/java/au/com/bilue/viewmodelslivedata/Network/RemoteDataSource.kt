package au.com.bilue.viewmodelslivedata.Network

import au.com.bilue.viewmodelslivedata.Model.GitOrganisation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by simonwestren on 10/1/18.
 */


interface GitOrganisationsService {
	@GET("organizations")
	fun getOrganisations(@Query("since") since : Int? = null) : Call<List<GitOrganisation>>
}

class GitOrganizationsHandler(val organisationsService: GitOrganisationsService) {
	fun organisations() : RetrofitLiveData<List<GitOrganisation>> = RetrofitLiveData(organisationsService.getOrganisations())
}

class RemoteDataSource private constructor() {

	companion object Singleton {
		val INSTANCE: RemoteDataSource by lazy { RemoteDataSource() }

		const val BASE_URL = "http://api.github.com"
	}

	val organisationHandler: GitOrganizationsHandler

	init {
		val gson = GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create()

		val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build()


		organisationHandler = GitOrganizationsHandler(retrofit.create(GitOrganisationsService::class.java))
	}
}