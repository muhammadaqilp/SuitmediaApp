package com.example.suitmediaapp.data.model

import com.example.suitmediaapp.R

object EventData {

    private val eventNames = arrayOf(
        "Big Bad Wolf",
        "Indocomtech",
        "We The Fest",
        "Pekan Raya Jakarta",
        "IIMS"
    )

    private val eventDate = arrayOf(
        "12 Februari 2021",
        "4 April 2021",
        "18 Juni 2021",
        "22 Juni 2021",
        "18 Agustus 2021"
    )

    private val eventImages = intArrayOf(
        R.drawable.big_bad_wolf,
        R.drawable.indocomtech,
        R.drawable.we_the_fest,
        R.drawable.pekan_raya_jakarta,
        R.drawable.iims
    )

    private val latDummy = arrayOf(
        "37.4267861",
        "37.4281350802915",
        "37.4254371197085",
        "40.7142484",
        "40.71559738029149",
    )

    private val lngDummy = arrayOf(
        "-122.0806032",
        "-122.0792542197085",
        "-122.0819521802915",
        "-73.9614103",
        "-73.9600613197085"
    )

    val listData: ArrayList<Event>
        get(){
            val list = arrayListOf<Event>()
            for (position in eventNames.indices){
                val event = Event()
                event.name = eventNames[position]
                event.date = eventDate[position]
                event.image = eventImages[position]
                event.lat = latDummy[position]
                event.lng = lngDummy[position]
                list.add(event)
            }
            return list
        }
}