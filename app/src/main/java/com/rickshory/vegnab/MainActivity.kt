package com.rickshory.vegnab

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),
    FragmentVisitAddEdit.VisitHeaderInterface,
    NavigationView.OnNavigationItemSelectedListener {

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


//        val visitsListFragment = FragmentVisitsList.newInstance()

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            Log.d(TAG, "fab action: start")
            showVisitAddEditFragment(null)
            Log.d(TAG, "fab action: exit")
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun showVisitAddEditFragment(visit: Visit?) {
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
    override fun onGoClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
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
            R.id.visits_action_new_proj -> editProject(null) // wrong type, but use for testing
//            R.id.main_action_settings -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    // wrong type, but use for testing
    private fun editProject(visit: Visit?) {
        Log.d(TAG, "editProject: start")
        // create a new fragment to edit the visit header
        val frag = FragmentVisitAddEdit.newInstance(visit)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, frag, Tags.Fragments.VISIT_HEADER)
            .addToBackStack(null)
            .commit()

        showDefaultLayout()
        Log.d(TAG, "editProject: exit")
    }

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
