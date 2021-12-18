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

        db.execSQL(DROP_USERS)
        db.execSQL(CREATE_USERS_TABLE)
        val user1Values = ContentValues()
        user1Values.put("first_name", "John")
        user1Values.put("middle_name", "")
        user1Values.put("last_name", "Smith")
        db.insert("users", OnConflictStrategy.REPLACE, user1Values)

        val user2Values = ContentValues()
        user2Values.put("first_name", "Ivan")
        user2Values.put("middle_name", "Ivanovich")
        user2Values.put("last_name", "Ivanov")
        db.insert("users", OnConflictStrategy.REPLACE, user2Values)

        db.execSQL(DROP_EVENTS)
        db.execSQL(REMAKE_EVENTS_TABLE)
        val event1Values = ContentValues()
        event1Values.put("user_id", 2)
        event1Values.put("event_type_id", 1)
        event1Values.put("date", 1638374884618L)
        db.insert("events", OnConflictStrategy.REPLACE, event1Values)

        val event2Values = ContentValues()
        event2Values.put("user_id", 1)
        event2Values.put("event_type_id", 2)
        event2Values.put("date", 1638374883618L)
        db.insert("events", OnConflictStrategy.REPLACE, event2Values)

        db.execSQL(DROP_EVENT_TYPES)
        db.execSQL(REMAKE_EVENT_TYPES_TABLE)
        val eventType1Values = ContentValues()
        eventType1Values.put("title", "birth day")
        eventType1Values.put("description", "I usually give money")
        db.insert("event_types", OnConflictStrategy.REPLACE, eventType1Values)

        val eventType2Values = ContentValues()
        eventType2Values.put("title", "wedding")
        eventType2Values.put("description", "")
        db.insert("event_types", OnConflictStrategy.REPLACE, eventType2Values)

    }

    companion object{
        private const val DROP_USERS = "DROP TABLE users;"
        private const val CREATE_USERS_TABLE = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                first_name TEXT,
                middle_name TEXT,
                last_name TEXT,
                phone TEXT,
                avatar TEXT
            );
        """

        private const val DROP_EVENTS = "DROP TABLE events;"
        const val REMAKE_EVENTS_TABLE = """
            CREATE TABLE events (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                event_type_id INTEGER,
                date INTEGER
            );
        """

        private const val DROP_EVENT_TYPES = "DROP TABLE event_types;"
        const val REMAKE_EVENT_TYPES_TABLE = """
            CREATE TABLE event_types (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT,
                description TEXT
            );
        """
    }

}
