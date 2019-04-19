package com.rickshory.vegnab

import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rickshory.vegnab.models.Visit
import com.rickshory.vegnab.ui.FragmentVisitAddEdit
import com.rickshory.vegnab.ui.FragmentVisitsList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(),
    FragmentVisitsList.VisitsListInterface,
    FragmentVisitAddEdit.VisitHeaderInterface,
    NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val TAG = this::class.java.simpleName
    }
    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        var ckFrag = supportFragmentManager.findFragmentByTag(Tags.Fragments.VISIT_HEADER)
        if (ckFrag != null) {
            // allow to do something if this fragment is up
            showDefaultLayout()
        } else {
//            fragment_container.visibility = View.VISIBLE
//            other_frag_container.view?.visibility = if(isLandscape) View.INVISIBLE else View.GONE
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // this this the right place to call showVisitsListFragment?
        showVisitsListFragment(null)
    }

    private fun showVisitsListFragment(visitsListOpts: VisitsListOpts?) {
        val frag = FragmentVisitsList.newInstance(visitsListOpts)
        val ct = supportFragmentManager.backStackEntryCount
        if (ct == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag, Tags.Fragments.VISITS_LIST)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag, Tags.Fragments.VISITS_LIST)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showVisitDetailFragment(visit: Visit?) {
        val frag = FragmentVisitAddEdit.newInstance(visit)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, frag, Tags.Fragments.VISIT_HEADER)
            .addToBackStack(null)
            .commit()
    }

    private fun showDefaultLayout() {
//            fragment_container.visibility = View.VISIBLE
//            other_frag_container.view?.visibility = if(isLandscape) View.VISIBLE else View.GONE
    }

    override fun visitsListOnGoClicked() {
        showVisitDetailFragment(null)
    }

    override fun visHeaderOnGoClicked() {

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
//            val frag = supportFragmentManager.findFragmentByTag(Tags.Fragments.VISIT_HEADER)
//            if (frag == null || isLandscape) {
//                super.onBackPressed()
//            } else {
//                // remove fragment
//            }
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
//            R.id.visits_action_new_proj -> editProject(null) // wrong type, but use for testing
            R.id.main_action_settings -> return true
//            android.R.id.home -> {
//                Log.d(TAG, "onOptionsItemSelected: Home button tapped")
//                val frag = supportFragmentManager.findFragmentByTag(Tags.Fragments.VISITS_LIST)
//                removeFragment(frag)
//            }
        }
        return super.onOptionsItemSelected(item)
    }

//    // wrong type, but use for testing
//    private fun editProject(visit: Visit?) {
//        Log.d(TAG, "editProject: start")
//        // create a new fragment to edit the visit header
//        val frag = FragmentVisitAddEdit.newInstance(visit)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, frag, Tags.Fragments.VISIT_HEADER)
//            .addToBackStack(null)
//            .commit()
//
//        showDefaultLayout()
//        Log.d(TAG, "editProject: exit")
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
