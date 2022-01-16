package ru.test.notifier.domain

// TODO: It is being used for testing and will be remove in the future.
data class ListObject(val id: Long, val title: String){

    override fun toString(): String {
        return title
    }
}