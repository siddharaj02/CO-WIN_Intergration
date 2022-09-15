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
import com.profilecreator.viewModel.OtherInfoViewModel
import com.profilecreator.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_company_details.Submit
import kotlinx.android.synthetic.main.fragment_other_info.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class OtherInfoFragment : Fragment() {
    private lateinit var otherInfoViewModel: OtherInfoViewModel
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

        Submit.setOnClickListener { view ->

            updateOtherInfo()

            Util.navigateToNextScreen(Submit, R.id.dashboardFragment)
        }

        setData()

    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_other_info, container, false)


        otherInfoViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(OtherInfoViewModel::class.java)

        otherInfoViewModel.otherInfoResult.observe(activity as DashboardActivity, Observer {
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

    private fun setData() {
        other_info.setText(MainApplication.profileDetails.otherInformation?.otherInformation)
    }

    private fun updateOtherInfo() {
        otherInfoViewModel.otherInfo(other_info.text.toString())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title = resources.getString(R.string.other_info)
    }
}