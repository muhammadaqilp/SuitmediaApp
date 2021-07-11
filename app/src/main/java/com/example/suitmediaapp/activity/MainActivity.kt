package com.example.suitmediaapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediaapp.R
import com.example.suitmediaapp.activity.MainActivity.Name.name
import com.example.suitmediaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        fun start(context: Context) {
            Intent(context, MainActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnNext.setOnClickListener {
            name = binding.insertName.text.toString()
            goToHome()
        }
    }

    private fun goToHome() {
        if (isPalindrome(name)) {
            showMessageDialog("$name isPalindrome")
        } else {
            showMessageDialog("$name notPalindrome")
        }
    }

    private fun showMessageDialog(s: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.checkPalindrome))
        builder.setMessage(s)

        builder.setPositiveButton("Ok") { dialog, _ ->
            dialog.cancel()
            startActivity(Intent(this, HomeActivity::class.java))
        }

        val alert = builder.create()
        alert.show()
    }

    private fun isPalindrome(_name: String): Boolean {
        var depan = 0
        var belakang = name.length - 1
        var name = _name

        name = name.lowercase()

        while (depan <= belakang){
            val getAtDepan = name[depan]
            val getAtBelakang = name[belakang]

            if (!(getAtDepan >= 'a' && getAtBelakang <= 'z')) depan++
            else if (getAtBelakang !in 'a'..'z') belakang--
            else if (getAtDepan == getAtBelakang){
                depan++
                belakang--
            }
            else return false
        }

        return true
    }

    object Name {
        lateinit var name: String
    }

}