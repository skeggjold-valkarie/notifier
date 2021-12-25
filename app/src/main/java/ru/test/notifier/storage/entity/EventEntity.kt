package ru.test.notifier.storage.entity

import androidx.room.*
import java.sql.Date


@Entity(
    tableName = "events",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EventTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_type_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "user_id")
    val user: Long,
    @ColumnInfo(name = "event_type_id")
    val eventType: Long,
    val date: Long
){
    constructor(user: Long, eventType: Long, date: Long): this(Long.MIN_VALUE, user, eventType, date)
}