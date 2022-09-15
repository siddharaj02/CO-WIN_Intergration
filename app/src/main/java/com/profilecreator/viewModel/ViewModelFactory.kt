package com.profilecreator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.profilecreator.dataSource.APIDataSource
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.repository.LoginRepository
import com.profilecreator.repository.NetworkRepository

/**
 * ViewModel provider factory to instantiate ViewModel.
 * Required given ViewModel has a non-empty constructor
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    loginRepository = LoginRepository(
                        apiDataSource = APIDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(PersonalInfoViewModel::class.java)) {

            return PersonalInfoViewModel(
                    networkRepository = NetworkRepository(
                    profileDataSource = ProfileDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(EducationDetailsViewModel::class.java)) {

            return EducationDetailsViewModel(
                networkRepository = NetworkRepository(
                    profileDataSource = ProfileDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(CompanyDetailsViewModel::class.java)) {

            return CompanyDetailsViewModel(
                networkRepository = NetworkRepository(
                    profileDataSource = ProfileDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(ProjectDetailsViewModel::class.java)) {

            return ProjectDetailsViewModel(
                networkRepository = NetworkRepository(
                    profileDataSource = ProfileDataSource()
                )
            ) as T
        } else if (modelClass.isAssignableFrom(OtherInfoViewModel::class.java)) {

            return OtherInfoViewModel(
                networkRepository = NetworkRepository(
                    profileDataSource = ProfileDataSource()
                )
            ) as T
        }


        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
