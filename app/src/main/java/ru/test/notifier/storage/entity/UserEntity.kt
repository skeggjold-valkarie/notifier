package ru.test.notifier.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "middle_name") val middleName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val phone: String?,
    val avatar: String?
)