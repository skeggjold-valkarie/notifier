package ru.test.notifier

import android.app.Application
import ru.test.notifier.navigation.Router
import ru.test.notifier.data.db.DataBaseRepository
import ru.test.notifier.ui.adapters.BitmapCache

class NotifierApplication : Application() {

    private val router: Router = Router()

    override fun onCreate() {
        super.onCreate()
        instance = this
        DataBaseRepository.init(this)
    }

    fun getRouter() = router

    fun getAppResources() = resources

    fun getBitmapCache() = BitmapCache()

    companion object{
        private lateinit var instance:NotifierApplication
        fun getInstance() = instance
    }
}





