package com.profilecreator.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.co_win_intergration.R
import com.profilecreator.ui.dashboard.DashboardFragment.OnListFragmentListener
import kotlinx.android.synthetic.main.fragment_dashboard_item.view.*


class DashboardAdapter(
    private val dashboardContentList: List<Int>,
    private val dashboardContentMap: MutableMap<Int, String>,
    private val mListener: OnListFragmentListener?
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { view ->
            val position = view.tag as Int
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(position, view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_dashboard_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resourceId = dashboardContentList[position]
        val resourceText = dashboardContentMap.get(resourceId)
        
       // holder.mIdView.setImageResource(resourceId)
        holder.mContentView.text = resourceText

        with(holder.mView) {
            tag = position
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = dashboardContentList.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: ImageView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
