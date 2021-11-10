package com.example.studyapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityPrayTime")
data class CityPrayTime (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "Id") val id : Int = 0, // this is how can include id if needed
    @ColumnInfo(name = "country") var country: String ,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "imsak") var imsak: String,
    @ColumnInfo(name = "sunrise") var sunrise: String,
    @ColumnInfo(name = "fajr") var fajr: String,
    @ColumnInfo(name = "dhuhr") var dhuhr: String,
    @ColumnInfo(name = "asr") var asr: String,
    @ColumnInfo(name = "maghrib") var maghrib: String,
    @ColumnInfo(name = "midnight") var midnight: String, )

