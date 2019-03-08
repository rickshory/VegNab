package com.rickshory.vegnab

import android.content.ContentResolver
import android.content.ContentValues
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

        testInsert()

        val projection = arrayOf(Contract_Namers.Columns.ID,
            Contract_Namers.Columns.NAMERS_NAME) // ignored
        val sortOrder = Contract_Namers.Columns.ID
//        val cursor = contentResolver.query(Contract_Namers.buildUriFromId(1),
        val cursor = contentResolver.query(Contract_Namers.CONTENT_URI,
//            projection,
            null,
            null,
            null,
            sortOrder)
        Log.d(TAG, "******************************************")
        cursor.use {
            while(it.moveToNext()) {
                // Cycle through all records
                with(it) {
                    val id = getLong(0)
                    val namer = getString(1)
                    val result = "ID: $id. Namer: $namer."
                    Log.d(TAG, "onCreate: reading data [$result]")
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

    private fun testInsert() {
        val values = ContentValues().apply {
            put(Contract_Namers.Columns.NAMERS_NAME, "Ada")
        }
        val uri = contentResolver.insert(Contract_Namers.CONTENT_URI, values)
        Log.d(TAG, "New row id (in uri) is $uri")
        Log.d(TAG, "id (in uri) = ${Contract_Namers.getID(uri)}")
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
