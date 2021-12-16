package ru.test.notifier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import ru.test.notifier.R
import androidx.core.view.GravityCompat
import android.view.View
import android.widget.Toast

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.navigation_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.edit_profile -> showToast("edit_profile")
            R.id.add_person -> showToast("add_person")
            R.id.edit_person -> showToast("edit_person")
            R.id.add_event -> showToast("add_event")
            R.id.notification -> showToast("notification")
            R.id.animation -> showToast("animation")
        }

        val drawer = findViewById<View>(R.id.main_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
            return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}