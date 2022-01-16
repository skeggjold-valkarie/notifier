package ru.test.notifier.data.db.entity

import androidx.room.*
import ru.test.notifier.domain.model.EventModel


@Entity(
    tableName = "events",
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
        ),
        ForeignKey(
            entity = EventTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_type_id"],
        )
    ]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "user_id") val personId: Long,
    @ColumnInfo(name = "event_type_id") val eventTypeId: Long,
    val date: Long
){
    fun mapToModel() = EventModel(id, personId, eventTypeId, date)
}