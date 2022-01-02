package ru.test.notifier.presenter.pages

import ru.test.notifier.storage.StorageRepository

class EventsPresenter (private val view: ContentView) {

    private val storage = StorageRepository.getInstance()

    fun getData() = storage.getAllEvents().map{ it.event }

    interface ContentView {

    }

}
