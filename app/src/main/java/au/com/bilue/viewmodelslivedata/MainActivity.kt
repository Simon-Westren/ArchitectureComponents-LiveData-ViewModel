package au.com.bilue.viewmodelslivedata

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import au.com.bilue.viewmodelslivedata.ViewModel.CityListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import au.com.bilue.viewmodelslivedata.Adapter.CityListAdapter
import au.com.bilue.viewmodelslivedata.Model.CityModel
import au.com.bilue.viewmodelslivedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

	private lateinit var cityList: CityListViewModel
	private lateinit var binding: ActivityMainBinding

	private var cityListOneShowing = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		cityList = ViewModelProviders.of(this).get(CityListViewModel::class.java)

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
