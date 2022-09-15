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
import com.profilecreator.viewModel.PersonalInfoViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_personal_info.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class PersonalInformationFragment : Fragment() {

    private lateinit var personalInfoViewModel:PersonalInfoViewModel

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

            updatePersonalDetails(false)

            Util.navigateToNextScreen(NEXT, R.id.EducationDetailsFragment)
        }

        Submit.setOnClickListener { view ->

            updatePersonalDetails(true)
        }

        setData()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personal_info, container, false)


        personalInfoViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(PersonalInfoViewModel::class.java)


        personalInfoViewModel.personalInfoResult.observe(activity as DashboardActivity, Observer {
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

    private fun updatePersonalDetails(saveInfo : Boolean) {
        personalInfoViewModel.personalInfo(saveInfo,
            firstName.text.toString(),
            lastName.text.toString(),
            phoneNumber.text.toString(),
            EmailId.text.toString(),
            address1.text.toString(),
            address2.text.toString(),
            DOB.text.toString()
        )
    }

    private fun setData() {
        firstName.setText(MainApplication.profileDetails.personalDetails?.firstName)
        lastName.setText(MainApplication.profileDetails.personalDetails?.lastName)
        phoneNumber.setText(MainApplication.profileDetails.personalDetails?.phoneNumber)
        EmailId.setText(MainApplication.profileDetails.personalDetails?.emailId)
        address1.setText(MainApplication.profileDetails.personalDetails?.address1)
        address2.setText(MainApplication.profileDetails.personalDetails?.address2)
        DOB.setText(MainApplication.profileDetails.personalDetails?.dateOfBirth)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.PersonalInformation)
    }


}
