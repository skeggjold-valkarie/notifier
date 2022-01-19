package ru.test.notifier.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.test.notifier.domain.model.PersonModel
import ru.test.notifier.domain.model.PresentModel

@Entity(
    tableName = "presents"
)
data class PresentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val sum: Double?
){
    fun mapToModel() = PresentModel(id, title, sum)
}