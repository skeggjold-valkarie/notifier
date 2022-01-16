package ru.test.notifier.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.test.notifier.domain.model.EventTypeModel
import ru.test.notifier.domain.model.PresentModel

@Entity(
    tableName = "event_types",
    indices = [Index(value = ["title"], unique = true)]
)
data class EventTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val description: String?
){
    fun mapToModel() = EventTypeModel(id, title, description)
}