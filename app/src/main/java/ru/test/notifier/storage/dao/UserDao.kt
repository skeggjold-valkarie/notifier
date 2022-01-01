package ru.test.notifier.storage.dao

import androidx.room.*
import ru.test.notifier.storage.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}