package com.profilecreator.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository

class CompanyDetailsViewModel(val networkRepository: NetworkRepository) : ViewModel() {

    val companyInfoResult= MediatorLiveData<ProfileDetails>()

    fun companyDetails(saveInfo : Boolean,
        companyName: String,
        designation: String,
        experience: String,
        awardsAndRecognition: String) {

        val result = networkRepository.saveCompanyInfo(saveInfo,
            companyName,
            designation,
            experience,
            awardsAndRecognition
        )

        result?.let {
            companyInfoResult.addSource(it)
            { profileDetailsLiveData -> companyInfoResult.setValue(profileDetailsLiveData) }
        }

    }

}