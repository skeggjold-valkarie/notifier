package ru.test.notifier.data.db

import android.content.Context
import androidx.room.Room
import ru.test.notifier.data.db.entity.EventEntity
import ru.test.notifier.data.db.entity.EventTypeEntity
import ru.test.notifier.data.db.extensions.mapToEntity
import ru.test.notifier.domain.model.EventModel
import ru.test.notifier.domain.model.EventTypeModel
import ru.test.notifier.domain.model.PersonModel
import java.util.*

class DataBaseRepository{

    fun getAllEvents() = database.getEventDao().getAll().map { it.mapToModel() }

    fun storeEvent(model: EventModel) = database.getEventDao().insert(model.mapToEntity())
    fun deleteEventById(id: Long) = database.getEventDao().deleteById(id)

    fun saveEventType(model: EventTypeModel) = database.getEventTypeDao().insert(model.mapToEntity())

    fun savePerson(model: PersonModel) = database.getUserDao().insert(model.mapToEntity())

    fun getAllUsers() = database.getUserDao().getAll() ?: emptyList()
    fun getAllEventTypes() = database.getEventTypeDao().getAll() ?: emptyList()

    companion object{

        private const val DATABASE_NAME = "notifier.db"
        private lateinit var database: AppDatabase

        private val instance = DataBaseRepository()

        fun init(context: Context) {
            if (Companion::database.isInitialized) return
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