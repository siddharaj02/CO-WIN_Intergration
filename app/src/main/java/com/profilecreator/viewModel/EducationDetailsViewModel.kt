package com.profilecreator.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository

class EducationDetailsViewModel (val networkRepository: NetworkRepository) : ViewModel() {

    val educationalInfoResult = MediatorLiveData<ProfileDetails>()

    fun educationDetails(saveInfo : Boolean,
         universityName1 : String,
         courseName1 : String,
         fromDate1 : String,
         toDate1 : String,

         universityName2 : String,
         courseName2 : String,
         fromDate2 : String,
         toDate2: String,

         universityName3 : String,
         courseName3 : String,
         fromDate3 : String,
         toDate3 : String

    ) {

        val result = networkRepository.saveEducationInfo(saveInfo,
            universityName1,
            courseName1,
            fromDate1,
            toDate1,

            universityName2,
            courseName2,
            fromDate2,
            toDate2,

            universityName3,
            courseName3,
            fromDate3,
            toDate3
        )

        result?.let {
            educationalInfoResult.addSource(it)
            { profileDetailsLiveData -> educationalInfoResult.setValue(profileDetailsLiveData) }
        }

    }


}