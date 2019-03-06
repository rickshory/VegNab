package com.rickshory.vegnab

import android.content.ContentResolver
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val projection = arrayOf(Contract_Projects.Columns.ID,
            Contract_Projects.Columns.PROJECT_CODE,
            Contract_Projects.Columns.PROJECT_DESCRIPTION)
        val sortOrder = Contract_Projects.Columns.PROJECT_START_DATE
        val cursor = contentResolver.query(Contract_Projects.CONTENT_URI,
            projection,
            null,
            null,
            sortOrder)
        Log.d(TAG, "******************************************")
        cursor.use {
            while(it.moveToNext()) {
                // Cycle through all records
                with(it) {
                    val id = getLong(0)
                    val projcode = getString(1)
                    val description = getString(2)
                    val result = "ID: $id ProjectCode: $projcode Description: $description"
                    Log.d(TAG, "onCreate: reading data [$result]")
/*      const val ID = BaseColumns._ID
        const val PROJECT_CODE = "ProjCode"
        const val PROJECT_DESCRIPTION = "Description"
        const val PROJECT_CONTEXT = "Context"
        const val PROJECT_CAVEATS = "Caveats"
        const val PROJECT_CONTACT_PERSON = "ContactPerson"
        const val PROJECT_START_DATE = "StartDate"
        const val PROJECT_END_DATE = "EndDate"
        const val PROJECT_HIDE_ON_MOBILE = "HideOnMobile"
        const val PROJECT_IS_DELETED = "IsDeleted"*/

                }
            }
        }
        Log.d(TAG, "******************************************")



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
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
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
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
