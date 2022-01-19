package ru.test.notifier.data.db.dao

import androidx.room.*
import ru.test.notifier.data.db.entity.EventTypeEntity

@Dao
interface EventTypeDao {

    @Query("SELECT * FROM event_types")
    fun getAll(): List<EventTypeEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(eventType: EventTypeEntity)

    @Update
    fun update(eventType: EventTypeEntity)

    @Delete
    fun delete(eventType: EventTypeEntity)
}