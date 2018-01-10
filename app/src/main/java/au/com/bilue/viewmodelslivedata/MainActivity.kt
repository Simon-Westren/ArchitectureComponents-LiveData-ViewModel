package au.com.bilue.viewmodelslivedata

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import au.com.bilue.viewmodelslivedata.ViewModel.CityListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import au.com.bilue.viewmodelslivedata.Adapter.CityListAdapter
import au.com.bilue.viewmodelslivedata.Model.CityModel
import au.com.bilue.viewmodelslivedata.Network.GitHubService
//import au.com.bilue.viewmodelslivedata.Network.RemoteDataSource
import au.com.bilue.viewmodelslivedata.ViewModel.GitOrganisationViewModel
import au.com.bilue.viewmodelslivedata.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


class MainActivity : AppCompatActivity() {

	private lateinit var cityList: CityListViewModel
	private lateinit var binding: ActivityMainBinding

	private var cityListOneShowing = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		cityList = ViewModelProviders.of(this).get(CityListViewModel::class.java)

//		ViewModelProviders.of(this)
//				.get(GitOrganisationViewModel::class.java)
//				.liveData.observe(this, Observer { response -> })
//
//		ViewModelProviders.of(this)
//				.get(GitOrganisationViewModel::class.java)
//				.liveData.observe(this, Observer { response -> Log.i("Got response", "Zen = " + response) })

		var retrofit = Retrofit.Builder()
				.baseUrl("https://api.github.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build()

		retrofit.create<GitHubService>(GitHubService::class.java).findZen().enqueue(object: Callback<String> {
			override fun onFailure(call: Call<String>?, t: Throwable?) {
				Log.i("Failed: ", "call: " + call + ", t: " + t)
			}

			override fun onResponse(call: Call<String>?, response: Response<String>?) {
				Log.i("Success", "response: " + response)
			}
		})

		setupRecyclerView()
		setupButton()
	}

	private fun setupRecyclerView() {
		binding.recyclerView.adapter = CityListAdapter(cityList)
		binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)

		cityList.cities.observe(this, Observer {
			binding.recyclerView.adapter.notifyDataSetChanged()
		})
	}

	private fun setupButton() {
		binding.button.setOnClickListener {
			if (cityListOneShowing) {
				cityList.cities.postValue(cityListOne())
			} else {
				cityList.cities.postValue(cityListTwo())
			}
			cityListOneShowing = !cityListOneShowing
			//RemoteDataSource.INSTANCE.zenHandler.zen().refresh()
		}
	}

	private fun cityListOne(): List<CityModel> {
		var cityListOne: MutableList<CityModel> = ArrayList()
		cityListOne.add(CityModel("Berlin"))
		cityListOne.add(CityModel("London"))
		cityListOne.add(CityModel("Paris"))

		return cityListOne
	}

	private fun cityListTwo(): List<CityModel> {
		var cityListTwo: MutableList<CityModel> = ArrayList()
		cityListTwo.add(CityModel("Sydney"))
		cityListTwo.add(CityModel("Melbourne"))
		cityListTwo.add(CityModel("Perth"))
		cityListTwo.add(CityModel("Brisbane"))

		return cityListTwo
	}
}
