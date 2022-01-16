package ru.test.notifier.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.test.notifier.domain.extensions.AUTO_GENERATE

@Parcelize
data class PersonModel (
    val id: Long = Long.AUTO_GENERATE,
    val firstName: String,
    val middleName: String?,
    val lastName: String?,
    val phone: String?,
    val avatar: String?
): Parcelable