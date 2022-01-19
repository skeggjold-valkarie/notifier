package ru.test.notifier.ui.model

data class CachedModel<T> (
    val position: Int,
    val model:T
)