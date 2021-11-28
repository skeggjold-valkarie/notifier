package ru.test.notifier.storage.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.test.notifier.storage.entity.UserEntity

interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): UserEntity?

    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}