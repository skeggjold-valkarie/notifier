package ru.test.notifier.ui

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import ru.test.notifier.R
import androidx.core.view.GravityCompat
import android.widget.Toast

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import ru.test.notifier.NotifierApplication
import ru.test.notifier.navigation.Router
import ru.test.notifier.presenter.MainPresenter


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    MainPresenter.ContentView
{

    private var presenter: MainPresenter? = null
    private var router: Router = NotifierApplication.getInstance().getRouter()

    private var drawer: DrawerLayout? = null
    private var navigationView: NavigationView? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindView()
        presenter = MainPresenter(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer?.addDrawerListener(toggle)
        toggle.syncState()

        navigationView?.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            router.showPage(this, Router.EVENTS_PAGE)
        }
    }

    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(GravityCompat.START) == true) {
            drawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val page = when (item.itemId) {
            R.id.menu_main -> Router.EVENTS_PAGE
            R.id.menu_profile -> Router.PROFILE_PAGE
            R.id.menu_persons -> Router.PERSONS_PAGE
            R.id.menu_event_types -> Router.EVENT_TYPES_PAGE
            R.id.menu_notification, R.id.menu_animation -> Router.SETTINGS_PAGE
            else -> Router.EVENTS_PAGE
        }
        router.showPage(this, page)

        drawer?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun bindView(){
        drawer = findViewById(R.id.main_layout)
        navigationView = findViewById(R.id.navigation_view)
        toolbar = findViewById(R.id.toolbar)
    }

}