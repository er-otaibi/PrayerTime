package com.example.praytimes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praytimes.model.Constant
import com.example.praytimes.model.MyId
import com.example.praytimes.model.MyViewModel
import com.example.praytimes.R
import com.example.praytimes.adapter.RVadapter
import com.example.studyapp.CityPrayTime
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var etSearch : EditText
    lateinit var btSearch : Button
    lateinit var btFav : Button
    lateinit var rvMain : RecyclerView
    lateinit var rVadapter: RVadapter
    var list = ArrayList<CityPrayTime>()
    var search =""
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        etSearch = findViewById(R.id.etSearch)
        btSearch = findViewById(R.id.btSearch)
        btFav = findViewById(R.id.btFav)
        rvMain = findViewById(R.id.rvMain)

        btFav.setOnClickListener {
            val intent = Intent(this , FavActivity::class.java)
            intent.putExtra("Fav" , "Favorite Time")
            startActivity(intent)
        }
        btSearch.setOnClickListener {
            search = etSearch.text.toString()

            if (search.isNotEmpty()){
                requestApi()
            }else {
                Toast.makeText(this , "Enter a Search name" , Toast.LENGTH_SHORT).show()
            }
            etSearch.text.clear()
        }

        rVadapter = RVadapter(this , list)
        rvMain.adapter = rVadapter
        rvMain.layoutManager = LinearLayoutManager(this)

    }

    private fun requestApi() {

        CoroutineScope(Dispatchers.IO).launch {

            val data = async {

                fetchData()

            }.await()

            if (data.isNotEmpty()) {

                updateData(data)
            }
        }
    }

    private suspend fun fetchData():String{

        var response=""
        try {
            response = URL(Constant.BASE_URL + search).readText(Charsets.UTF_8)

        }catch (e:Exception)
        {

            withContext(Dispatchers.Main) {

                Toast.makeText(applicationContext , "city is invalid" , Toast.LENGTH_SHORT).show()

            }
            println("Error $e")

        }
        return response

    }

    private suspend fun updateData(data:String) {
        withContext(Dispatchers.Main) {

            val jsonObject = JSONObject(data)
            val results = jsonObject.getJSONObject("results").getJSONArray("datetime").getJSONObject(0).getJSONObject("times")
            var fajer = results.getString("Fajr")
            var dhuhr = results.getString("Dhuhr")
            var asr = results.getString("Asr")
            var imsak = results.getString("Imsak")
            var sunrise = results.getString("Sunrise")
            var maghrib = results.getString("Maghrib")
            var midnight = results.getString("Midnight")

            var cityCountry = jsonObject.getJSONObject("results").getJSONObject("location")
            var city = cityCountry.getString("city")
            var country = cityCountry.getString("country")

             list.add(CityPrayTime(MyId.id +1,country,city,imsak,sunrise , fajer ,dhuhr, asr, maghrib, midnight))
             rVadapter.update(list)


        }

    }
}