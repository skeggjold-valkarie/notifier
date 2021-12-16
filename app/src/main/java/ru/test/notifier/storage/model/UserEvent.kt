package ru.test.notifier.storage.model

import androidx.room.ColumnInfo
import java.util.*

data class UserEvent(
    @ColumnInfo(name = "first_name")val firstName: String,
    @ColumnInfo(name = "middle_name")val middleName: String,
    @ColumnInfo(name = "last_name")val lastName: String,
    val avatar: String?,
    val date: Long,
    @ColumnInfo(name = "title")val event: String
)