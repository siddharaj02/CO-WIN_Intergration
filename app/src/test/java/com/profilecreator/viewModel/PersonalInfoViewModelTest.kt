package com.profilecreator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.PersonalDetails
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PersonalInfoViewModelTest {

    private lateinit var viewModel: PersonalInfoViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val profileDataSource by lazy { ProfileDataSource() }
    private val networkRepository by lazy { NetworkRepository(profileDataSource) }

    private val personalDetails = PersonalDetails("dd", "dhsdh", "dhd", "dhsdgh",
            "sfgsdf", "sdgdf", "sgasg")

    private val profileDetails = ProfileDetails(null, null, null, personalDetails)


    @Before
    fun init() {
        viewModel = PersonalInfoViewModel(networkRepository)
    }


    @Test
    fun testLiveData() {
        viewModel.personalInfoResult.postValue(profileDetails)

        assertEquals(profileDetails, viewModel.personalInfoResult.value)
    }


    @Test
    fun tryPersonalDetails_and_getPersonalInfoResult() {


        val result: LiveData<ProfileDetails>? = viewModel.networkRepository.savePersonalInfo(true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh",
            "sfgsdf",
            "sdgdf",
            "sgasg")

        result?.let {
            viewModel.personalInfoResult.addSource(it)
            { profileDetailsLiveData -> viewModel.personalInfoResult.setValue(profileDetailsLiveData) }


            assertEquals(it.value, MainApplication.profileDetails)
        }
    }

}