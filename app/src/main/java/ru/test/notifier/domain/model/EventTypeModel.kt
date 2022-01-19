package ru.test.notifier.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.test.notifier.domain.extensions.AUTO_GENERATE

@Parcelize
data class EventTypeModel (
    val id: Long = Long.AUTO_GENERATE,
    val title: String,
    val description: String?
): Parcelable