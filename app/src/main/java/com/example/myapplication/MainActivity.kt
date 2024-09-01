package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var isAllFabVisibility = false
        binding.extendFabShow.shrink()
        binding.extendFabShow.setOnClickListener {
            isAllFabVisibility = if (isAllFabVisibility) {
                hide()
                false
            } else {
                show()
                true
            }
        }
        }

    private fun show() {
        binding.fabEdit.show()
        binding.fabDelete.show()
        binding.fabShare.show()
        binding.extendFabShow.extend()
    }

    private fun hide() {
        binding.fabEdit.hide()
        binding.fabDelete.hide()
        binding.fabShare.hide()
        binding.extendFabShow.shrink()
    }

    fun onClick(view: View){
        when (view.id){
            R.id.fab_edit -> toast("ویرایش کردن")
            R.id.fab_delete -> toast("حذف کردن")
            R.id.fab_share -> toast("اشتراک گذاری کردن")
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}
