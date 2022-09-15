package com.profilecreator.model

data class ProfileDetails(
    var companyDetails: CompanyDetails? = null,
    var educationDetails: EducationDetails? = null,
    var otherInformation: OtherInformation? = null,
    var personalDetails: PersonalDetails? = null,
    var projectDetails: ProjectDetails? = null,
    var user: User? = null,

    var data: Data? = null,
    var success: String? = null,
    var id: String? = null,
    val error: Int? = null,
    var parentId: String? = null,
    var exception: Exception? = null
)
