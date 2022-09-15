package com.profilecreator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.CompanyDetails
import com.profilecreator.model.ProfileDetails
import com.profilecreator.repository.NetworkRepository
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CompanyDetailsViewModelTest {

    private lateinit var viewModel: CompanyDetailsViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val profileDataSource by lazy { ProfileDataSource() }
    private val networkRepository by lazy { NetworkRepository(profileDataSource) }

    private val companyDetails = CompanyDetails("dd", "dhsdh", "dhd", "dhsdgh")

    private val profileDetails = ProfileDetails(companyDetails)


    @Before
    fun init() {
        viewModel = CompanyDetailsViewModel(networkRepository)
    }


    @Test
    fun testLiveData() {
        viewModel.companyInfoResult.postValue(profileDetails)

        Assert.assertEquals(profileDetails, viewModel.companyInfoResult.value)
    }


    @Test
     fun tryCompanyDetails_and_getCompanyInfoResult() {


        val result: LiveData<ProfileDetails>? = viewModel.networkRepository.saveCompanyInfo(true,
                "dd",
                "dhsdh",
                "dhd",
                "dhsdgh")


        result?.let {
            viewModel.companyInfoResult.addSource(it)
            { profileDetailsLiveData -> viewModel.companyInfoResult.setValue(profileDetailsLiveData) }


            Assert.assertEquals(it.value, MainApplication.profileDetails)
        }
    }

}