package com.example.suitmediaapp.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.suitmediaapp.R
import com.example.suitmediaapp.adapter.ListGuestAdapter
import com.example.suitmediaapp.data.model.GuestItem
import com.example.suitmediaapp.data.remote.ServiceBuilder
import com.example.suitmediaapp.data.remote.UserApiClient
import com.example.suitmediaapp.databinding.ActivityGuestBinding
import com.example.suitmediaapp.db.DatabaseContract
import com.example.suitmediaapp.db.GuestHelper
import com.example.suitmediaapp.db.helper.MappingHelper
import kotlinx.android.synthetic.main.activity_guest.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestActivity : AppCompatActivity(), ListGuestAdapter.OnGuestClickCallback {

    private lateinit var binding: ActivityGuestBinding
    private lateinit var guestHelper: GuestHelper
    private var list: ArrayList<GuestItem> = arrayListOf()
    private val request = ServiceBuilder.buildService(UserApiClient::class.java)
    private val listGuestAdapter = ListGuestAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.guestToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvGuest.setHasFixedSize(true)
        binding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        binding.rvGuest.adapter = listGuestAdapter

        cacheData()

        swipeRefresh()
    }

    private fun cacheData() {
        guestHelper = GuestHelper.getInstance(applicationContext)
        guestHelper.open()

        CoroutineScope(Dispatchers.Main).launch(Dispatchers.Main) {
            val defferedUser = async(Dispatchers.IO) {
                val cursor = guestHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            val guestData = defferedUser.await()
            Log.d("NGECEK KELAS", guestData.toString())
            if (!guestData.isNullOrEmpty()) {

                listGuestAdapter.setData(guestData)

                Log.d("BERHASIL DITEMUKAN", guestData.toString())
            } else {
                Log.d("Data KOSONG", "tidak ada")

                showData()
            }
        }
    }

    private fun swipeRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            cacheData()
        }
    }

    private fun showData() {
        val call = request.getListUser("596dec7f0f000023032b8017")
        binding.rvGuest.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        call.enqueue(object : Callback<List<GuestItem>> {
            override fun onResponse(
                call: Call<List<GuestItem>>,
                response: Response<List<GuestItem>>
            ) {
                if (response.isSuccessful) {
                    binding.progressBar.visibility = View.GONE
                    binding.rvGuest.visibility = View.VISIBLE
                    listGuestAdapter.setData(response.body() as ArrayList<GuestItem>)
                    insertData(response.body() as ArrayList<GuestItem>)
                }
            }

            override fun onFailure(call: Call<List<GuestItem>>, t: Throwable) {
                Toast.makeText(this@GuestActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun insertData(body: ArrayList<GuestItem>) {
        for (i in 0 until body.size){
            val values = ContentValues()
            values.put(DatabaseContract.GuestColumn.ID, body[i].id)
            values.put(DatabaseContract.GuestColumn.NAME, body[i].name ?: "")
            values.put(DatabaseContract.GuestColumn.BIRTHDATE, body[i].birthdate)

            guestHelper.insert(values)
        }
    }

    override fun onGuestClicked(data: GuestItem) {

        val phone = checkBirthDate(data)
        val isPrime = checkMonth(data)

        val primeMonth = (isPrime)

        val intent = Intent()
        intent.putExtra(HomeActivity.GUEST_STRING, data.name)
        setResult(HomeActivity.GUEST_ACTIVITY, intent)
        Toast.makeText(this, "$phone and $primeMonth", Toast.LENGTH_SHORT).show()
        finish()

    }

    private fun checkBirthDate(data: GuestItem): String {
        val birthDate = data.birthdate.toString()
        val date = birthDate.slice(8..9).toInt()

        return if (date % 2 == 0 && date % 3 == 0) {
            "iOS"
        } else if (date % 3 == 0) {
            "Android"
        } else if (date % 2 == 0) {
            "Blackberry"
        } else {
            "Feature Phone"
        }
    }

    private fun checkMonth(data: GuestItem): String {

        val birthMonth = data.birthdate.toString()
        val month = birthMonth.slice(5..6).toInt()

        if (month <= 1) {
            return "Not Prime"
        } else {
            for (i in 2 until month) {
                if (month % i === 0) {
                    return "Not Prime"
                }
            }
        }
        return "Is Prime"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
