package com.erenkara.personsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erenkara.personsapp.entity.Person
import com.erenkara.personsapp.entity.PersonDb

@Database(entities = [PersonDb::class], version = 1)
abstract class DatabaseClass : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        var INSTANCE: DatabaseClass? = null

        fun accessDatabase(context: Context): DatabaseClass? {
            if (INSTANCE == null) {
                synchronized(DatabaseClass::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseClass::class.java,
                        "guide.sqlite"
                    ).createFromAsset("guide.sqlite").build()
                }
            }
            return INSTANCE
        }
    }
}