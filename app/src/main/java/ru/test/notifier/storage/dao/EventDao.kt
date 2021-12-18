package ru.test.notifier.storage.dao

import androidx.room.*
import ru.test.notifier.storage.entity.EventEntity
import ru.test.notifier.storage.entity.UserEntity

@Dao
interface EventDao {

    @Transaction
    @Query("SELECT * FROM events")
    fun getAll(): List<EventEntity>?

    @Insert
    fun insert(event: EventEntity)

    @Update
    fun update(event: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}