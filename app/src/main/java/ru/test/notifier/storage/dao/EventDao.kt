package ru.test.notifier.storage.dao

import androidx.room.*
import ru.test.notifier.storage.entity.EventEntity
import ru.test.notifier.storage.entity.UserEntity
import ru.test.notifier.storage.model.UserEvent

@Dao
interface EventDao {

//    @Transaction
//    @Query("SELECT * FROM events")
//    fun getAll(): List<EventEntity>?

    @Query("SELECT users.first_name, users.middle_name, users.last_name, users.avatar, " +
            "events.date, event_types.title FROM events " +
            "JOIN users ON events.user_id == users.id " +
            "JOIN event_types ON events.event_type_id == event_types.id")
    fun getAll(): List<UserEvent>

    @Insert
    fun insert(event: EventEntity)

    @Update
    fun update(event: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}