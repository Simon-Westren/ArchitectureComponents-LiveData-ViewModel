package au.com.bilue.viewmodelslivedata

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import au.com.bilue.viewmodelslivedata.ViewModel.CityListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import au.com.bilue.viewmodelslivedata.Adapter.CityListAdapter
import au.com.bilue.viewmodelslivedata.Model.CityModel


class MainActivity : AppCompatActivity() {

	lateinit var cityList: CityListViewModel
	lateinit var recyclerView: RecyclerView

	var cityListOneShowing = true

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupRecyclerView()
		setupButton()
	}

	fun setupRecyclerView() {
		cityList = ViewModelProviders.of(this).get(CityListViewModel::class.java)

		recyclerView = findViewById(R.id.recyclerView)
		recyclerView.adapter = CityListAdapter(cityList)
		recyclerView.layoutManager = LinearLayoutManager(baseContext)

		cityList.cities.observe(this, Observer {
			recyclerView.adapter.notifyDataSetChanged()
		})
	}

	fun setupButton() {
		val button = findViewById<Button>(R.id.button)
		button.setOnClickListener {
			if (cityListOneShowing) {
				cityList.cities.postValue(cityListOne())
			} else {
				cityList.cities.postValue(cityListTwo())
			}
			cityListOneShowing = !cityListOneShowing
		}
	}

	fun cityListOne(): List<CityModel> {
		var cityListOne: MutableList<CityModel> = ArrayList()
		cityListOne.add(CityModel("Berlin"))
		cityListOne.add(CityModel("London"))
		cityListOne.add(CityModel("Paris"))

		return cityListOne
	}

	fun cityListTwo(): List<CityModel> {
		var cityListTwo: MutableList<CityModel> = ArrayList()
		cityListTwo.add(CityModel("Sydney"))
		cityListTwo.add(CityModel("Melbourne"))
		cityListTwo.add(CityModel("Perth"))
		cityListTwo.add(CityModel("Brisbane"))

		return cityListTwo
	}
}
