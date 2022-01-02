package ru.test.notifier.presenter.pages

import ru.test.notifier.storage.StorageRepository

class EventTypesPresenter(private val view: ContentView) {

    private val storage = StorageRepository.getInstance()

    fun getData() = storage.getAllEventTypes().mapNotNull { it.title }

    interface ContentView {

    }

}