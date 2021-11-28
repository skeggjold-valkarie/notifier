package ru.test.notifier.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import ru.test.notifier.storage.entity.EventEntity

@Dao
interface EventDao {

    @Insert
    fun insert(event: EventEntity)

    @Update
    fun update(event: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}