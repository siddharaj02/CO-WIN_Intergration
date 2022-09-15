package com.profilecreator.model

data class NameValuePairs(
    val firstName : String,
    val lastName : String,
    val phoneNumber : String,
    val emailId : String,
    val address1 : String,
    val address2 : String,
    val dateOfBirth : String,

    val universityName1 : String,
    val courseName1 : String,
    val fromDate1 : String,
    val toDate1 : String,


    val universityName2 : String,
    val courseName2 : String,
    val fromDate2 : String,
    val toDate2 : String,


    val universityName3 : String,
    val courseName3 : String,
    val fromDate3 : String,
    val toDate3 : String,


    val companyName : String,
    val designation : String,
    val experience : String,
    val awardsAndRecognitions : String,

    val projectTitle1 : String,
    val technology1 : String,
    val duration1 : String,
    val description1 : String,

    val projectTitle2 : String,
    val technology2 : String,
    val duration2 : String,
    val description2 : String,

    val otherInformation : String,

    val data: Data? = null,
    val success: String? = null,
    val id: String? = null,
    val error: Int? = null,
    val parentId: String? = null,
    var exception: Exception? = null
    )