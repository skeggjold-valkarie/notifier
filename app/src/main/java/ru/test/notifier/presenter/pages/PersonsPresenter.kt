package ru.test.notifier.presenter.pages

import ru.test.notifier.data.db.DataBaseRepository

class PersonsPresenter(private val view: ContentView) {

    private val storage = DataBaseRepository.getInstance()

    fun getData() = storage.getAllUsers().mapNotNull { it.firstName }

    interface ContentView {

    }

}