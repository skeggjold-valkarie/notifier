package ru.test.notifier.data.db.entity

import androidx.room.ColumnInfo
import ru.test.notifier.domain.model.PersonEventModel

data class PersonEventEntity(
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "middle_name") val middleName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val avatar: String?,
    @ColumnInfo(name = "id") val eventId: Long,
    val date: Long,
    @ColumnInfo(name = "title") val event: String
){
    fun mapToModel() = PersonEventModel(firstName, middleName, lastName, avatar, eventId, date, event)
}