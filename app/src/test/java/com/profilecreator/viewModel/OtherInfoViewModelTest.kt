package com.profilecreator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.OtherInformation
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OtherInfoViewModelTest {
    private lateinit var viewModel: OtherInfoViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val profileDataSource by lazy { ProfileDataSource() }
    private val networkRepository by lazy { NetworkRepository(profileDataSource) }

    private val otherInformation = OtherInformation("dd")

    private val profileDetails = ProfileDetails(null,
            null,
            otherInformation,
            null,
            null)

    @Before
    fun init() {
        viewModel = OtherInfoViewModel(networkRepository)
    }

    @Test
    fun testLiveData() {
        viewModel.otherInfoResult.postValue(profileDetails)

        assertEquals(profileDetails, viewModel.otherInfoResult.value)
    }


    @Test
    fun tryOtherDetails_and_getOtherInfoResult() {


        val result: LiveData<ProfileDetails>? = viewModel.networkRepository.saveOtherInfo("dd")


        result?.let {
            viewModel.otherInfoResult.addSource(it)
            { profileDetailsLiveData -> viewModel.otherInfoResult.setValue(profileDetailsLiveData) }


            assertEquals(it.value, MainApplication.profileDetails)
        }
    }

}