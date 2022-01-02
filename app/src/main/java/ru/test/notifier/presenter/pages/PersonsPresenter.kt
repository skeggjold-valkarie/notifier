package ru.test.notifier.presenter.pages

import ru.test.notifier.storage.StorageRepository

class PersonsPresenter(private val view: ContentView) {

    private val storage = StorageRepository.getInstance()

    fun getData() = storage.getAllUsers().mapNotNull { it.firstName }

    interface ContentView {

    }

}