package ru.test.notifier.storage

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class InitialDatabaseCallBack: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        println("databaseLog Database has been created.")
    }


    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
//        println("databaseLog is db ${db is AppDatabase}")
        println("databaseLog Database has been opened.")
    }

//    private fun populateDatabase(){
//        //Clearing all the data from table
//        wordDao.deleteAll()
//
//        //Adding record
//        var word = Word("Hello")
//        wordDao.insert(word)
//
//        //Adding record
//        word = Word("World!")
//        wordDao.insert(word)
//    }

}
