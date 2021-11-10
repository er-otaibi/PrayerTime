package com.example.praytimes.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.studyapp.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(application: Application): AndroidViewModel(application) {

    private val prayerDao = PrayerDatabase.getInstance(application).prayerDao()
    private val prayerTime: LiveData<List<CityPrayTime>> = prayerDao.getPrayerTimes()

    fun getPrayerTime(): LiveData<List<CityPrayTime>> {

        return prayerTime
    }

     fun addPrayerTime(cityPrayTime: CityPrayTime){
        CoroutineScope(Dispatchers.IO).launch {

          prayerDao.insertPrayerTime(cityPrayTime)
        }

    }

    fun updatePrayerTime(cityPrayTime: CityPrayTime){
        CoroutineScope(Dispatchers.IO).launch {

            prayerDao.updatePrayerTime(cityPrayTime)

        }
    }

    fun deletePrayerTime(cityPrayTime: CityPrayTime){
        CoroutineScope(Dispatchers.IO).launch {

            prayerDao.deletePrayerTime(cityPrayTime)

        }
    }



}