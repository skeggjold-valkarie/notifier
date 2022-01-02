package ru.test.notifier.storage

import android.content.Context
import androidx.room.Room
import ru.test.notifier.storage.entity.EventEntity
import ru.test.notifier.storage.entity.EventTypeEntity
import ru.test.notifier.storage.entity.UserEntity
import java.util.*

class StorageRepository{

    fun getAllEvents() = database.getEventDao().getAll()

    fun saveEvent(userId: Long?, typeId: Long?, date: Date){
        if (userId == null || typeId == null) return
        database.getEventDao().insert(
           EventEntity(user = userId, eventType = typeId, date = date.time)
        )
    }

    fun saveEventType(title: String, description: String?){
        database.getEventTypeDao().insert(
            EventTypeEntity(title = title, description = description ?: "")
        )
    }


    fun savePerson(firstName: String, middleName: String, lastName: String, phone: String?, avatar: String?){
        database.getUserDao().insert(
            UserEntity(id = 0, firstName = firstName, middleName = middleName,
                lastName = lastName, phone = phone ?: "", avatar = avatar ?: ""
            )
        )
    }


    fun getAllUsers() = database.getUserDao().getAll() ?: emptyList()
    fun getAllEventTypes() = database.getEventTypeDao().getAll() ?: emptyList()

    companion object{

        private const val DATABASE_NAME = "notifier.db"
        private lateinit var database: AppDatabase

        private val instance = StorageRepository()

        fun init(context: Context) {
            if (::database.isInitialized) return
            database = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries() // TODO: remove and call dao methods in async way
                .fallbackToDestructiveMigration()
                .addCallback(InitialDatabaseCallBack())
                .build()

            println("databaseLog is db ${database.getUserDao().getAll()}")
            println("databaseLog is db ${database.getEventTypeDao().getAll()}")
            println("databaseLog is db ${database.getEventDao().getAll()}")
        }

        fun getInstance() = instance

    }
}