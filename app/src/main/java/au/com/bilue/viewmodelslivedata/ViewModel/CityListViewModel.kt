package au.com.bilue.viewmodelslivedata.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import au.com.bilue.viewmodelslivedata.Model.CityModel

/**
 * Created by simonwestren on 9/1/18.
 */

class CityListViewModel: ViewModel() {

	var cities: MutableLiveData<List<CityModel>> = MutableLiveData()

}