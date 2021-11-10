package com.example.praytimes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.praytimes.activities.FavActivity
import com.example.praytimes.model.MyViewModel
import com.example.praytimes.R
import com.example.studyapp.CityPrayTime
import kotlinx.android.synthetic.main.item_row.view.*

class FavAdapter(private val activity: FavActivity, private var list: ArrayList<CityPrayTime>):  RecyclerView.Adapter<FavAdapter.ItemViewHolder>(){

    private val myViewModel by lazy { ViewModelProvider(activity).get(MyViewModel::class.java) }

    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var data = list[position]

        holder.itemView.apply {
            tvCity.text = data.city
            tvCountry.text = data.country
            tvFajer.text = data.fajr
            tvDhuhr.text = data.dhuhr
            tvAsr.text = data.asr
            tvMaghrib.text = data.maghrib
            tvMidnight.text = data.midnight
            tvSunrise.text = data.sunrise
            tvImsak.text = data.imsak

            var checked = true
            cbFav.isChecked = true

            cbFav.setOnCheckedChangeListener { _, _ ->
                if (checked)  {
                    myViewModel.deletePrayerTime(CityPrayTime(data.id,data.country,data.city,data.imsak,data.sunrise,data.fajr,
                        data.dhuhr, data.asr,data.maghrib,data.midnight))
                }else {

                    myViewModel.addPrayerTime(CityPrayTime(data.id,data.country,data.city,data.imsak,data.sunrise,data.fajr,
                        data.dhuhr, data.asr,data.maghrib,data.midnight))

                    checked = !checked
                }
            }
        }
    }


    override fun getItemCount() = list.size

    fun update(list: ArrayList<CityPrayTime>){
        this.list = list
        notifyDataSetChanged()
    }
}