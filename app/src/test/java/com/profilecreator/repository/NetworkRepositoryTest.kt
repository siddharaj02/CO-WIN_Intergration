package com.profilecreator.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.ProfileDetails
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NetworkRepositoryTest {

    private lateinit var networkRepository: NetworkRepository

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val profileDataSource by lazy { ProfileDataSource() }

    @Before
    fun setUp() {
        networkRepository = NetworkRepository(profileDataSource)
    }

    @Test
    fun savePersonalInfo() {

        val result: LiveData<ProfileDetails>? = networkRepository.savePersonalInfo(
            true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh",
            "sfgsdf",
            "sdgdf",
            "sgasg"
        )

        Assert.assertEquals(result?.value, MainApplication.profileDetails)
    }

    @Test
    fun saveEducationInfo() {

        val result: LiveData<ProfileDetails>? = networkRepository.saveEducationInfo(
            true,
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
            "fsdf"
        )

        Assert.assertEquals(result?.value, MainApplication.profileDetails)
    }

    @Test
    fun saveCompanyInfo() {

        val result: LiveData<ProfileDetails>? = networkRepository.saveCompanyInfo(
            true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh"
        )

        Assert.assertEquals(result?.value, MainApplication.profileDetails)

    }

    @Test
    fun saveProjectDetails() {

        val result: LiveData<ProfileDetails>? = networkRepository.saveProjectDetails(
            true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh",

            "sfgsdf",
            "sdgdf",
            "sgsg",
            "sdgasg"
        )

        Assert.assertEquals(result?.value, MainApplication.profileDetails)
    }

    @Test
    fun saveOtherInfo() {

        val result: LiveData<ProfileDetails>? = networkRepository.saveOtherInfo("dd")

        Assert.assertEquals(result?.value, MainApplication.profileDetails)
    }


}