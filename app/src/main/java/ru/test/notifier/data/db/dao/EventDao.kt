package ru.test.notifier.data.db.dao

import androidx.room.*
import ru.test.notifier.data.db.entity.EventEntity
import ru.test.notifier.data.db.entity.PersonEventEntity

@Dao
interface EventDao {

//    @Transaction
//    @Query("SELECT * FROM events")
//    fun getAll(): List<EventEntity>?

    @Query("SELECT users.first_name, users.middle_name, users.last_name, users.avatar, " +
            "events.id, events.date, event_types.title FROM events " +
            "JOIN users ON events.user_id == users.id " +
            "JOIN event_types ON events.event_type_id == event_types.id")
    fun getAll(): List<PersonEventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: EventEntity)

    @Update
    fun update(event: EventEntity)

    @Query("DELETE FROM events WHERE id = :id")
    fun deleteById(id: Long)
}