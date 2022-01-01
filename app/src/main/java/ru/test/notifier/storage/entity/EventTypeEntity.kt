package ru.test.notifier.storage.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "event_types",
    indices = [Index(value = ["title"], unique = true)]
)
data class EventTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String?
){
    constructor(title: String, description: String?): this(0, title, description)
}