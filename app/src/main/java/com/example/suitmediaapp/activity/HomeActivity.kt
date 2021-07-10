package com.example.suitmediaapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmediaapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    companion object {
        const val EVENT_ACTIVITY = 1
        const val GUEST_ACTIVITY = 0
        const val EVENT_STRING = "EVENT_STRING"
        const val GUEST_STRING = "GUEST_STRING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.txtNama.text = MainActivity.Name.name

        binding.btnEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivityForResult(intent, EVENT_ACTIVITY)
        }

        binding.btnGuest.setOnClickListener {
            val intent = Intent(this, GuestActivity::class.java)
            startActivityForResult(intent, GUEST_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EVENT_ACTIVITY){
            val result = data?.getStringExtra(EVENT_STRING)
            if (result?.isNotEmpty() == true){
                binding.btnEvent.text = result
            }
        }
        if (requestCode == GUEST_ACTIVITY){
            val result = data?.getStringExtra(GUEST_STRING)
            if (result?.isNotEmpty() == true){
                binding.btnGuest.text = result
            }
        }
    }
}