package com.example.roomexample_yena

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactLog::class], version = 1)
abstract class ContactLogDatabase : RoomDatabase() {

    abstract fun contactLogDao() : ContactLogDao

    companion object {
        private var INSTANCE : ContactLogDatabase? = null

        fun getInstance(context : Context): ContactLogDatabase? {
            if(INSTANCE == null){
              synchronized(ContactLogDatabase::class){
                  INSTANCE = Room.databaseBuilder(context.applicationContext,
                      ContactLogDatabase::class.java, "contactLog")
                      .fallbackToDestructiveMigration()
                      .build()
              }
            }
            return INSTANCE
        }

    }


}