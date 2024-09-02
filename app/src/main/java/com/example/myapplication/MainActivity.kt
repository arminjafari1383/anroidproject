package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnShow.setOnClickListener{
            val snack = Snackbar.make(binding.testRoot,"نبود اینترنت",Snackbar.LENGTH_INDEFINITE)
            snack.setAction("خروج"){
                toast("Test")
            }
            snack.setTextColor(Color.WHITE)
            snack.setActionTextColor(Color.RED)
            snack.setBackgroundTint(Color.BLACK)
            ViewCompat.setLayoutDirection(snack.view,ViewCompat.LAYOUT_DIRECTION_RTL)
            snack.show()
        }
    }
    private fun toast(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}
