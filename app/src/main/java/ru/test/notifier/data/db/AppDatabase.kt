package ru.test.notifier.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.notifier.data.db.dao.EventDao
import ru.test.notifier.data.db.dao.EventTypeDao
import ru.test.notifier.data.db.dao.UserDao
import ru.test.notifier.data.db.entity.EventEntity
import ru.test.notifier.data.db.entity.EventTypeEntity
import ru.test.notifier.data.db.entity.PersonEntity

@Database(
    entities = [
        EventEntity::class,
        EventTypeEntity::class,
        PersonEntity::class
    ],
    version = 4
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getEventDao(): EventDao
    abstract fun getEventTypeDao(): EventTypeDao
}