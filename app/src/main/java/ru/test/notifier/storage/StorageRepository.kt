package ru.test.notifier.storage

import android.content.Context
import androidx.room.Room

class StorageRepository{

//    fun getAllUsers() = database.getUserDao().getAll()

    companion object{

        private const val DATABASE_NAME = "notifier.db"
        private lateinit var database: AppDatabase

        fun init(context: Context) {
            if (::database.isInitialized) return
            database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries() // remove and call dao methods in async way
                .fallbackToDestructiveMigration()
                .addCallback(InitialDatabaseCallBack())
                .build()

            println("databaseLog is db ${database.getEventDao().getAll()}")
        }

    }
}