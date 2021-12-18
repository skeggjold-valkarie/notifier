package ru.test.notifier.storage.dao

import androidx.room.*
import ru.test.notifier.storage.entity.EventTypeEntity
import ru.test.notifier.storage.entity.UserEntity

@Dao
interface EventTypeDao {

    @Query("SELECT * FROM event_types")
    fun getAll(): List<EventTypeEntity>?

    @Insert
    fun insert(eventType: EventTypeEntity)

    @Update
    fun update(eventType: EventTypeEntity)

    @Delete
    fun delete(eventType: EventTypeEntity)
}