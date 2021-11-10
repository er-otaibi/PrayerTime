package com.example.praytimes.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praytimes.model.MyViewModel
import com.example.praytimes.R
import com.example.praytimes.adapter.FavAdapter
import com.example.studyapp.CityPrayTime

class FavActivity : AppCompatActivity() {
    lateinit var btBack : Button
    lateinit var rvFav : RecyclerView
    lateinit var favAdapter : FavAdapter
    var list = ArrayList<CityPrayTime>()
    private val myViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        btBack = findViewById(R.id.btBack)
        rvFav = findViewById(R.id.rvFav)


        myViewModel.getPrayerTime().observe(this, {
                prayTime ->
            favAdapter.update(prayTime as ArrayList<CityPrayTime>)
        })

        favAdapter = FavAdapter(this ,list )
        rvFav.adapter = favAdapter
        rvFav.layoutManager = LinearLayoutManager(this)

        title = intent!!.getStringExtra("Fav")

        btBack.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}