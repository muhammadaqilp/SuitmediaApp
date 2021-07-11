package com.example.suitmediaapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediaapp.R
import com.example.suitmediaapp.adapter.ListEventAdapter
import com.example.suitmediaapp.data.model.Event
import com.example.suitmediaapp.data.model.EventData
import com.example.suitmediaapp.databinding.ActivityEventBinding
import com.example.suitmediaapp.fragment.MapViewFragment

class EventActivity : AppCompatActivity(), ListEventAdapter.OnItemClickCallback{

    private lateinit var binding: ActivityEventBinding
    private var list: ArrayList<Event> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.eventToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvEvent.setHasFixedSize(true)

        list.addAll(EventData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        val listEventAdapter = ListEventAdapter(list, this)
        binding.rvEvent.adapter = listEventAdapter
    }

    override fun onItemClicked(data: Event) {
        val intent = Intent()
        intent.putExtra(HomeActivity.EVENT_STRING, data.name)
        setResult(HomeActivity.EVENT_ACTIVITY, intent)
        Toast.makeText(this, "Event: "+data.name, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        if (item.itemId == R.id.addmedia){
            binding.containerMap.visibility = View.VISIBLE
            var mapView = MapViewFragment()
            supportFragmentManager.beginTransaction().add(R.id.containerMap, mapView)
                .commit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}