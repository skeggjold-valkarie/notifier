package ru.test.notifier.storage.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.test.notifier.storage.entity.EventTypeEntity

interface EventTypeDao {

    @Insert
    fun insert(eventType: EventTypeEntity)

    @Update
    fun update(eventType: EventTypeEntity)

    @Delete
    fun delete(eventType: EventTypeEntity)
}