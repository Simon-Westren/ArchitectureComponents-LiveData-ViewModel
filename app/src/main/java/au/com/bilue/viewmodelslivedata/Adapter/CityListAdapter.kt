package au.com.bilue.viewmodelslivedata.Adapter

import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import au.com.bilue.viewmodelslivedata.ViewModel.CityListViewModel
import au.com.bilue.viewmodelslivedata.databinding.CityViewBinding

/**
 * Created by simonwestren on 9/1/18.
 */

class CityListAdapter(val cityList: CityListViewModel) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(CityViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.city = cityList.cities.value?.get(position)
	}

	override fun getItemCount(): Int {
		return cityList.cities.value?.size ?: 0
	}

	class ViewHolder(val binding: CityViewBinding) : RecyclerView.ViewHolder(binding.root)
}

