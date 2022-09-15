package com.profilecreator.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository

class PersonalInfoViewModel(val networkRepository: NetworkRepository) : ViewModel() {

    val personalInfoResult = MediatorLiveData<ProfileDetails>()

    fun personalInfo(saveInfo : Boolean,
        firstName: String,
        lastName: String,
        phoneNum: String,
        emailID: String,
        address1: String,
        address2: String,
        dob: String) {

        val result = networkRepository.savePersonalInfo(saveInfo,
            firstName,
            lastName,
            phoneNum,
            emailID,
            address1,
            address2,
            dob)

        result?.let {
            personalInfoResult.addSource(it)
            { profileDetailsLiveData -> personalInfoResult.setValue(profileDetailsLiveData) }
        }

    }


}