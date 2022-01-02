package ru.test.notifier.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import ru.test.notifier.R
import ru.test.notifier.view.screens.*

class Router {

    fun showPage(activity: FragmentActivity, tag: String) {
        val fragment = when(tag){
            MAIN_PAGE -> EventsFragment()
            PROFILE_PAGE -> ProfileFragment()
            EVENTS_PAGE -> EventTypesFragment()
            PERSONS_PAGE -> PersonsFragment()
            SETTINGS_PAGE -> SettingsFragment()
            else -> return
        }
        activity.supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_host, fragment, tag)
        }
    }

    companion object{
        const val MAIN_PAGE = "mainFragment"
        const val PROFILE_PAGE = "profileFragment"
        const val EVENTS_PAGE = "eventsFragment"
        const val PERSONS_PAGE = "personsFragment"
        const val SETTINGS_PAGE = "settingsFragment"
    }
}