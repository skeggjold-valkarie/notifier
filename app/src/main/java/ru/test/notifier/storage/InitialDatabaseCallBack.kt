package ru.test.notifier.storage

import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class InitialDatabaseCallBack: RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        println("databaseLog Database has been created.")
    }


    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        println("databaseLog Database has been opened.")
        populateDatabase(db)
    }

    private fun populateDatabase(db: SupportSQLiteDatabase){

        db.execSQL(DROP_USER)
        db.execSQL(CREATE_USER_TABLE)
        val userValues = ContentValues()
        userValues.put("first_name", "first")
        userValues.put("middle_name", "middle")
        userValues.put("last_name", "last")
        db.insert("users", OnConflictStrategy.REPLACE, userValues)


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
    }

    companion object{
        private const val DROP_USER = "DROP TABLE users;"
        private const val CREATE_USER_TABLE = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                first_name TEXT,
                middle_name TEXT,
                last_name TEXT,
                phone TEXT,
                avatar TEXT
            );
        """

        const val REMAKE_EVENT_TABLE = """
            DROP TABLE events;
            CREATE TABLE events (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                event_type_id INTEGER,
                date INTEGER
            );
        """

        const val REMAKE_EVENT_TYPE_TABLE = """
            DROP TABLE event_types;
            CREATE TABLE event_types (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT
            );
        """
    }

}
