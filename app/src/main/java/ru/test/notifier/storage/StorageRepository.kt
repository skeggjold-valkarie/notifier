package ru.test.notifier.storage


class StorageRepository{

    fun getAllUsers() = database.getUserDao().getAll()

    companion object{
        private lateinit var database: AppDatabase

    }
}