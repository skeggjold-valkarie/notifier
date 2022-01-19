package ru.test.notifier.data.db.dao

import androidx.room.*
import ru.test.notifier.data.db.entity.PersonEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): List<PersonEntity>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): PersonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: PersonEntity)

    @Update
    fun update(user: PersonEntity)

    @Delete
    fun delete(user: PersonEntity)
}