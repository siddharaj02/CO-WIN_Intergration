package com.profilecreator.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository

class ProjectDetailsViewModel (val networkRepository: NetworkRepository) : ViewModel() {

    val projectInfoResult = MediatorLiveData<ProfileDetails>()

    fun projectDetails(saveInfo : Boolean,
         projectTitle1 : String,
         technology1 : String,
         duration1 : String,
         description1 : String,

         projectTitle2 : String,
         technology2 : String,
         duration2 : String,
         description2 : String) {

        val result = networkRepository.saveProjectDetails(saveInfo,
            projectTitle1,
            technology1,
            duration1,
            description1,

            projectTitle2,
            technology2 ,
            duration2,
            description2
        )
        result?.let {
            projectInfoResult.addSource(it)
            { profileDetailsLiveData -> projectInfoResult.setValue(profileDetailsLiveData) }
        }
    }


}