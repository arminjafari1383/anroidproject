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
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnShow.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Test")
            dialog.setMessage("ActivityMainBinding.inflate")
            dialog.setCancelable(false)
            dialog.setPositiveButton("ok"){_,_ ->
                toast("ok")
            }
            dialog.setNegativeButton("Cancel"){_,_ ->
                toast("cancel")
            }
            dialog.setNeutralButton("setting"){_,_ ->
                toast("setting")
            }
            dialog.create().show()
        }
    }
    private fun toast(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}
