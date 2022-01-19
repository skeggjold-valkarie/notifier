package ru.test.notifier.data.db.extensions

import ru.test.notifier.data.db.entity.*
import ru.test.notifier.domain.model.*

fun EventModel.mapToEntity() = EventEntity(id, personId, eventTypeId, date)

fun EventTypeModel.mapToEntity() = EventTypeEntity(id, title, description)

fun PersonModel.mapToEntity() = PersonEntity(id, firstName, middleName, lastName, phone, avatar)

fun PersonEventModel.mapToEntity() =
    PersonEventEntity(firstName, middleName, lastName, avatar, eventId, date, event)

fun PresentModel.mapToEntity() = PresentEntity(id, title, sum)