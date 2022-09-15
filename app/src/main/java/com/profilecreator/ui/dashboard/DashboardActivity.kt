package com.profilecreator.ui.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.Navigation.createNavigateOnClickListener
import com.co_win_intergration.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*

class DashboardActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    DashboardFragment.OnListFragmentListener {

    override fun onListFragmentInteraction(position: Int, view: View) {
        when(position) {
            0-> view.setOnClickListener(createNavigateOnClickListener(R.id.PersonalInformationFragment))
            1-> view.setOnClickListener(createNavigateOnClickListener(R.id.EducationDetailsFragment))
            2-> view.setOnClickListener(createNavigateOnClickListener(R.id.CompanyDetailsFragment))
            3-> view.setOnClickListener(createNavigateOnClickListener(R.id.ProjectDetailsFragment))
            4-> view.setOnClickListener(createNavigateOnClickListener(R.id.OtherInfoFragment))
        }
    }

    private lateinit  var menuM: Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuM = menu
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setOnMenuItemClickListener { item: MenuItem ->
            onOptionsItemSelected(item)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            R.id.navigateNext -> {

            }
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
