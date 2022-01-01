package ru.test.notifier.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.notifier.storage.dao.EventDao
import ru.test.notifier.storage.dao.EventTypeDao
import ru.test.notifier.storage.dao.UserDao
import ru.test.notifier.storage.entity.EventEntity
import ru.test.notifier.storage.entity.EventTypeEntity
import ru.test.notifier.storage.entity.UserEntity

@Database(
    entities = [
        EventEntity::class,
        EventTypeEntity::class,
        UserEntity::class
    ],
    version = 3
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getEventDao(): EventDao
    abstract fun getEventTypeDao(): EventTypeDao
}