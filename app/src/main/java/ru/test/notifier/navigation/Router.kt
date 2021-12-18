package ru.test.notifier.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import ru.test.notifier.R
import ru.test.notifier.view.screens.EventsFragment
import ru.test.notifier.view.screens.SettingsFragment

class Router {

    fun showPage(activity: FragmentActivity, tag: String) {
        val fragment = when(tag){
            EVENTS_PAGE -> EventsFragment()
            SETTINGS_PAGE -> SettingsFragment()
            else -> return
        }
        activity.supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_host, fragment, tag)
        }
    }

    companion object{
        const val EVENTS_PAGE = "eventsFragment"
        const val SETTINGS_PAGE = "settingsFragment"
    }
}