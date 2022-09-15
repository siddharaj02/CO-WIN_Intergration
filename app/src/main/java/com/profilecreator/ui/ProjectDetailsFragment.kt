package com.profilecreator.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.co_win_intergration.R
import com.profilecreator.ui.dashboard.DashboardActivity
import com.profilecreator.ui.util.MainApplication
import com.profilecreator.ui.util.Util
import com.profilecreator.viewModel.ProjectDetailsViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_project_details.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ProjectDetailsFragment : Fragment() {

    private lateinit var projectDetailsViewModel: ProjectDetailsViewModel
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NEXT.setOnClickListener { view ->
            updateProjectDetails(false)

            Util.navigateToNextScreen(NEXT, R.id.OtherInfoFragment)
        }

        Submit.setOnClickListener { view ->

            updateProjectDetails(true)
        }

        setData()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_project_details, container, false)

        projectDetailsViewModel = ViewModelProviders.of(this, ViewModelFactory())
        .get(ProjectDetailsViewModel::class.java)

        projectDetailsViewModel.projectInfoResult.observe(activity as DashboardActivity, Observer {
            val result = it ?: return@Observer

            if (result.error != null) {
                Util.showResult(activity as DashboardActivity, "Saving data: " + result.error.toString())
            }
            if (result.success != null) {
                Util.showResult(activity as DashboardActivity, "Saving data: " + result.success.toString())
            }
        })

        return view
    }
    private fun updateProjectDetails(saveInfo : Boolean) {
        projectDetailsViewModel.projectDetails(saveInfo,
            projectTitle1.text.toString(),
            technology1.text.toString(),
            duration1.text.toString(),
            description1.text.toString(),

            projectTitle2.text.toString(),
            technology2.text.toString(),
            duration2.text.toString(),
            description2.text.toString()
        )
    }

    private fun setData() {
        projectTitle1.setText(MainApplication.profileDetails.projectDetails?.projectTitle1)
        technology1.setText(MainApplication.profileDetails.projectDetails?.technology1)
        duration1.setText(MainApplication.profileDetails.projectDetails?.duration1)
        description1.setText(MainApplication.profileDetails.projectDetails?.description1)

        projectTitle2.setText(MainApplication.profileDetails.projectDetails?.projectTitle2)
        technology2.setText(MainApplication.profileDetails.projectDetails?.technology2)
        duration2.setText(MainApplication.profileDetails.projectDetails?.duration2)
        description2.setText(MainApplication.profileDetails.projectDetails?.description2)
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.Project_details)
    }
}