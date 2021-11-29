package ru.test.notifier

import android.app.Application
import ru.test.notifier.storage.StorageRepository

class NotifierApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StorageRepository.init(this)
    }

}