package ru.test.notifier

import android.app.Application
import ru.test.notifier.navigation.Router
import ru.test.notifier.storage.StorageRepository

class NotifierApplication : Application() {

    private val router: Router = Router()

    override fun onCreate() {
        super.onCreate()
        instance = this
        StorageRepository.init(this)
    }

    fun getRouter() = router

    companion object{
        private lateinit var instance:NotifierApplication
        fun getInstance() = instance
    }
}