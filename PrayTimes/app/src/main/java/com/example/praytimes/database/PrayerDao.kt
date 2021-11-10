package com.example.studyapp

import androidx.lifecycle.LiveData
import androidx.room.*

//Data Access Object
@Dao
interface PrayerDao {

    @Query("SELECT * FROM CityPrayTime")
    fun getPrayerTimes(): LiveData<List<CityPrayTime>>

    @Insert
    fun insertPrayerTime(cityPrayTime: CityPrayTime)

    @Update
    fun updatePrayerTime(cityPrayTime: CityPrayTime)

    @Delete
    fun deletePrayerTime(cityPrayTime: CityPrayTime)

}