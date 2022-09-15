package com.profilecreator.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.*
import com.profilecreator.ui.util.MainApplication


class NetworkRepository(val profileDataSource: ProfileDataSource) {

 private var result : MutableLiveData<ProfileDetails>? = null

    fun savePersonalInfo(saveInfo : Boolean,
                         firstName: String,
                         lastName: String,
                         phoneNum: String,
                         emailID: String,
                         address1: String,
                         address2: String,
                         dob: String) : LiveData<ProfileDetails>? {

         var personalDetails = PersonalDetails(firstName, lastName, phoneNum,emailID, address1,address2,dob)

        MainApplication.profileDetails.personalDetails = personalDetails

       if(saveInfo) {
           var result = profileDataSource.saveProfileInfo()
           return result
       }

        return result
    }

    fun saveEducationInfo(saveInfo : Boolean,
        universityName1 : String,
                           courseName1 : String,
                           fromDate1 : String,
                           toDate1 : String,

                           universityName2 : String,
                           courseName2 : String,
                           fromDate2 : String,
                           toDate2 : String,

                           universityName3 : String,
                           courseName3 : String,
                           fromDate3 : String,
                           toDate3 : String) : LiveData<ProfileDetails>?{

        var educationDetails = EducationDetails(universityName1,
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

        MainApplication.profileDetails.educationDetails = educationDetails


        if(saveInfo) {
            var result = profileDataSource.saveProfileInfo()
            return result
        }
        return result
    }

    fun saveCompanyInfo( saveInfo : Boolean,
                         companyName: String,
                         designation: String,
                         experience: String,
                         awardsAndRecognition: String) : LiveData<ProfileDetails>?{

       var companyDetails =
           CompanyDetails(
           companyName,
           designation,
           experience,
           awardsAndRecognition
           )

        MainApplication.profileDetails.companyDetails = companyDetails

        if(saveInfo) {
            var result = profileDataSource.saveProfileInfo()
            return result
        }
        return result
    }

    fun saveProjectDetails(saveInfo : Boolean,projectTitle1 : String,
                           technology1 : String,
                           duration1 : String,
                           description1 : String,

                           projectTitle2 : String,
                           technology2 : String,
                           duration2 : String,
                           description2 : String) : LiveData<ProfileDetails>?{

        var projectDetails = ProjectDetails(
                projectTitle1,
                technology1,
                duration1,
                description1,

                projectTitle2,
                technology2 ,
                duration2,
                description2
            )

        MainApplication.profileDetails.projectDetails = projectDetails

        if(saveInfo) {
            var result = profileDataSource.saveProfileInfo()
            return result
        }
        return result
    }


     fun saveOtherInfo(otherInformation: String) : LiveData<ProfileDetails> ? {

        var otherInformation = OtherInformation(otherInformation)

         MainApplication.profileDetails.otherInformation = otherInformation

        return saveProfileInfo()
    }

    private fun saveProfileInfo(): LiveData<ProfileDetails>? {
        return profileDataSource.saveProfileInfo()
    }
}