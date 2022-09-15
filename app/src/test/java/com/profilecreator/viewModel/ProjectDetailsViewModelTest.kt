package com.profilecreator.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.profilecreator.dataSource.ProfileDataSource
import com.profilecreator.model.ProfileDetails
import com.profilecreator.model.ProjectDetails
import com.profilecreator.repository.NetworkRepository
import com.profilecreator.ui.util.MainApplication
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProjectDetailsViewModelTest {

    private lateinit var viewModel: ProjectDetailsViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    private val profileDataSource by lazy { ProfileDataSource() }
    private val networkRepository by lazy { NetworkRepository(profileDataSource) }

    private val projectDetails = ProjectDetails("dd", "dhsdh", "dhd", "dhsdgh",
            "sfgsdf", "sdgdf", "sgsg", "sdgasg")

    private val profileDetails = ProfileDetails(null,
            null,
            null,
            null,
            projectDetails)

    @Before
    fun init() {
        viewModel = ProjectDetailsViewModel(networkRepository)
    }

    @Test
    fun testLiveData() {
        viewModel.projectInfoResult.postValue(profileDetails)

        assertEquals(profileDetails, viewModel.projectInfoResult.value)
    }


    @Test
    fun tryProjectDetails_and_getProjectInfoResult() {

        val result: LiveData<ProfileDetails>? = viewModel.networkRepository.saveProjectDetails(true,
            "dd",
            "dhsdh",
            "dhd",
            "dhsdgh",

            "sfgsdf",
            "sdgdf",
            "sgsg",
            "sdgasg")

        result?.let {
            viewModel.projectInfoResult.addSource(it)
            { profileDetailsLiveData -> viewModel.projectInfoResult.setValue(profileDetailsLiveData) }


            assertEquals(it.value, MainApplication.profileDetails)
        }
    }

}