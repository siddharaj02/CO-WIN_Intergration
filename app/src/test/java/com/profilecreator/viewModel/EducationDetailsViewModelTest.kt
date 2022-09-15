package com.profilecreator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.EducationDetails
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EducationDetailsViewModelTest {


    private lateinit var viewModel: EducationDetailsViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val profileDataSource by lazy { ProfileDataSource() }
    private val networkRepository by lazy { NetworkRepository(profileDataSource) }

    private val educationDetails = EducationDetails("dd", "dhsdh", "dhd", "dhsdgh",
            "sfgsdf", "sdgdf", "sgasg", "adsgasfg ",
            "fasdg", "fasg", "fasdg", "fsdf")

    private val profileDetails = ProfileDetails(null, educationDetails)


    @Before
    fun init() {
        viewModel = EducationDetailsViewModel(networkRepository)
    }


    @Test
    fun testLiveData() {
        viewModel.educationalInfoResult.postValue(profileDetails)

        Assert.assertEquals(profileDetails, viewModel.educationalInfoResult.value)
    }


    @Test
    fun tryEducationDetails_and_getEducationInfoResult() {


        val result: LiveData<ProfileDetails>? = viewModel.networkRepository.saveEducationInfo(true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh",

            "sfgsdf",
            "sdgdf",
            "sgasg",
            "adsgasfg ",

            "fasdg",
            "fasg",
            "fasdg",
            "fsdf")


        result?.let {
            viewModel.educationalInfoResult.addSource(it)
            { profileDetailsLiveData -> viewModel.educationalInfoResult.setValue(profileDetailsLiveData) }


            Assert.assertEquals(it.value, MainApplication.profileDetails)
        }
    }
}