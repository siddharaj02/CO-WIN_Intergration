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
import com.profilecreator.viewModel.EducationDetailsViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_education_details.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EducationDetailsFragment : Fragment() {
    private lateinit var educationDetailsViewModel:EducationDetailsViewModel

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

            updateEducationDetails(false)

            Util.navigateToNextScreen(NEXT, R.id.CompanyDetailsFragment)
        }

        Submit.setOnClickListener { view ->

            updateEducationDetails(true)

        }

        setData()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_education_details, container, false)

        educationDetailsViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(EducationDetailsViewModel::class.java)


        educationDetailsViewModel.educationalInfoResult.observe(activity as DashboardActivity, Observer {
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

    private fun updateEducationDetails(saveInfo : Boolean) {
            educationDetailsViewModel.educationDetails(saveInfo,
            universityName1.text.toString(),
            courseName1.text.toString(),
            fromDate1.text.toString(),
            toDate1.text.toString(),

            universityName2.text.toString(),
            courseName2.text.toString(),
            fromDate2.text.toString(),
            toDate2.text.toString(),

            universityName3.text.toString(),
            courseName3.text.toString(),
            fromDate3.text.toString(),
            toDate3.text.toString()
        )
    }

    private fun setData() {
        universityName1.setText(MainApplication.profileDetails.educationDetails?.universityName1)
        courseName1.setText(MainApplication.profileDetails.educationDetails?.courseName1)
        fromDate1.setText(MainApplication.profileDetails.educationDetails?.fromDate1)
        toDate1.setText(MainApplication.profileDetails.educationDetails?.toDate1)

        universityName2.setText(MainApplication.profileDetails.educationDetails?.universityName2)
        courseName2.setText(MainApplication.profileDetails.educationDetails?.courseName2)
        fromDate2.setText(MainApplication.profileDetails.educationDetails?.fromDate2)
        toDate2.setText(MainApplication.profileDetails.educationDetails?.toDate2)

        universityName3.setText(MainApplication.profileDetails.educationDetails?.universityName3)
        courseName3.setText(MainApplication.profileDetails.educationDetails?.courseName3)
        fromDate3.setText(MainApplication.profileDetails.educationDetails?.fromDate3)
        toDate3.setText(MainApplication.profileDetails.educationDetails?.toDate3)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.Education_Details)
    }
}