package com.example.studyapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityPrayTime::class],version = 1,exportSchema = false)
abstract class PrayerDatabase: RoomDatabase() {

    companion object{
        var instance:PrayerDatabase?=null;
        fun getInstance(ctx: Context):PrayerDatabase
        {
            if(instance!=null)
            {
                return  instance as PrayerDatabase;
            }
            instance= Room.databaseBuilder(ctx,PrayerDatabase::class.java,"Prayer.db").run { allowMainThreadQueries() }.build()
            return instance as PrayerDatabase;
        }
    }
    abstract fun prayerDao():PrayerDao;
}