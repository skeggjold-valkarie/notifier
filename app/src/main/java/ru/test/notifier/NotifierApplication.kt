package ru.test.notifier

import android.app.Application
import android.graphics.BitmapFactory
import android.util.Log
import ru.test.notifier.navigation.Router
import ru.test.notifier.storage.StorageRepository
import ru.test.notifier.ui.adapters.BitmapCache
import ru.test.notifier.ui.extensions.toBitmap

class NotifierApplication : Application() {

    private val router: Router = Router()

    override fun onCreate() {
        super.onCreate()
        instance = this
        StorageRepository.init(this)
    }

    fun getRouter() = router

    fun getAppResources() = resources

    fun getBitmapCache() = BitmapCache()

    companion object{
        private lateinit var instance:NotifierApplication
        fun getInstance() = instance
    }
}