package au.com.bilue.viewmodelslivedata.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import au.com.bilue.viewmodelslivedata.Model.CityModel

/**
 * Created by simonwestren on 9/1/18.
 */

class CityViewModel(val city: LiveData<CityModel>): ViewModel() {

	val name = {
		city.value?.name
	}
}