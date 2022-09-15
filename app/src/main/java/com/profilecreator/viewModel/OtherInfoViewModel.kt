package com.profilecreator.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository


class OtherInfoViewModel(val networkRepository: NetworkRepository) : ViewModel() {
    val otherInfoResult = MediatorLiveData<ProfileDetails>()

    fun otherInfo(otherInformation: String) {

        val result = networkRepository.saveOtherInfo(otherInformation)

        result?.let {
            otherInfoResult.addSource(it)
            { profileDetailsLiveData -> otherInfoResult.setValue(profileDetailsLiveData) }
        }
    }


}