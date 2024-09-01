package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var doubleclick = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {
        if (doubleclick){
            super.onBackPressed()
            return
        }
        doubleclick = true
        toast("برای خروح دو بار کلیک کنید")

        Handler(Looper.getMainLooper()).postDelayed({doubleclick = false},2000)
    }
    private fun toast(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}
