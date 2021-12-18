package ru.test.notifier.storage.dao

import androidx.room.*
import ru.test.notifier.storage.entity.EventEntity
import ru.test.notifier.storage.entity.UserEntity

@Dao
interface EventDao {

    @Query("SELECT * FROM events")
    fun getAll(): List<UserEntity>?

    @Insert
    fun insert(event: EventEntity)

    @Update
    fun update(event: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}