package ru.test.notifier.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonEventModel (
    val firstName: String,
    val middleName: String?,
    val lastName: String?,
    val avatar: String?,
    val eventId: Long,
    val date: Long,
    val event: String
): Parcelable