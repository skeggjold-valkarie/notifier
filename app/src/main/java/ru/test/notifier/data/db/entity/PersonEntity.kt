package ru.test.notifier.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.test.notifier.domain.model.PersonModel

@Entity(tableName = "users")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "middle_name") val middleName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    val phone: String?,
    val avatar: String?
){
    fun mapToModel() = PersonModel(id, firstName, middleName, lastName, phone, avatar)
}