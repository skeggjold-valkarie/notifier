package ru.test.notifier.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.test.notifier.domain.extensions.AUTO_GENERATE

@Parcelize
data class EventModel (
    var id: Long = Long.AUTO_GENERATE,
    val personId: Long,
    val eventTypeId: Long,
    val date: Long
): Parcelable