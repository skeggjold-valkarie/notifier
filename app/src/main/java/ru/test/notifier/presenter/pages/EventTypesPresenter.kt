package ru.test.notifier.presenter.pages

import ru.test.notifier.data.db.DataBaseRepository

class EventTypesPresenter(private val view: ContentView) {

    private val storage = DataBaseRepository.getInstance()

    fun getData() = storage.getAllEventTypes().mapNotNull { it.title }

    interface ContentView {

    }

}