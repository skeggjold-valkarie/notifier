package ru.test.notifier.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "presents"
)
data class PresentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String?,
    val sum: Double?
)