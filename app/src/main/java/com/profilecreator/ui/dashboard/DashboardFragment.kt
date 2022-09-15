package com.profilecreator.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.co_win_intergration.R
import com.profilecreator.ui.adapter.DashboardAdapter
import com.profilecreator.ui.util.Util


class DashboardFragment : Fragment() {

    private var listener: OnListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.title =
            resources.getString(R.string.profile)

        //init PersonalDetails in application class to access it from anywhere
        Util.initProfileDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {

                adapter = DashboardAdapter(
                    DashboardContent.DASHBOARD_ITEM_LIST,
                    DashboardContent.DASHBOARD_ITEM_MAP as MutableMap<Int, String>, listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentListener {
        fun onListFragmentInteraction(position: Int, view: View)
    }

}
