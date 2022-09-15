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
import com.profilecreator.viewModel.CompanyDetailsViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_company_details.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CompanyDetailsFragment : Fragment() {
    private lateinit var companyDetailsViewModel: CompanyDetailsViewModel

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
            if (validateFormDetails(
                    companyName.text.toString(),
                    designation.text.toString(),
                    experience.text.toString())){
                updateCompanyDetails(false)

                Util.navigateToNextScreen(NEXT, R.id.ProjectDetailsFragment)
            } else {
                Util.showResult(activity as DashboardActivity, getString(R.string.empty_field))
            }
        }

        Submit.setOnClickListener { view ->
            if (validateFormDetails(
                    companyName.text.toString(),
                    designation.text.toString(),
                    experience.text.toString())){
                updateCompanyDetails(true)
            } else{
                Util.showResult(activity as DashboardActivity, getString(R.string.empty_field))
            }

            // updateCompanyDetails(true)
        }

        setData()

    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_company_details, container, false)


        companyDetailsViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(CompanyDetailsViewModel::class.java)


        companyDetailsViewModel.companyInfoResult.observe(activity as DashboardActivity, Observer {
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

    private fun updateCompanyDetails(saveInfo : Boolean) {
            companyDetailsViewModel.companyDetails(saveInfo,
            companyName.text.toString(),
            designation.text.toString(),
            experience.text.toString(),
            Awards_and_recognitions.text.toString())
    }

    private fun setData() {
        companyName.setText(MainApplication.profileDetails.companyDetails?.companyName)
        designation.setText(MainApplication.profileDetails.companyDetails?.designation)
        experience.setText(MainApplication.profileDetails.companyDetails?.experience)
        Awards_and_recognitions.setText(MainApplication.profileDetails.companyDetails?.awardsAndRecognitions)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.CompanyDetails)
    }

    private fun validateFormDetails(companyName: String, designation: String, experience: String): Boolean {
        return !(companyName.isEmpty() || designation.isEmpty() || experience.isEmpty())

    }
}