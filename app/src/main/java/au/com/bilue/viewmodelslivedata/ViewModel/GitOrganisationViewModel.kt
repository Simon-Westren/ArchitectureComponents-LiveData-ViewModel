package au.com.bilue.viewmodelslivedata.ViewModel

import android.arch.lifecycle.ViewModel
import au.com.bilue.viewmodelslivedata.Model.GitOrganisation
import au.com.bilue.viewmodelslivedata.Network.RemoteDataSource
import au.com.bilue.viewmodelslivedata.Network.RetrofitLiveData

/**
 * Created by simonwestren on 10/1/18.
 */

class GitOrganisationViewModel : ViewModel() {

	val liveData : RetrofitLiveData<List<GitOrganisation>> = RemoteDataSource.INSTANCE.organisationHandler.organisations()

	override fun onCleared() {
		liveData.cancel()
	}
}