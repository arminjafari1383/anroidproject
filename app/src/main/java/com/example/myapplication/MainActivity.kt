package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val cameraRequestCode = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnGetPermission.setOnClickListener {

            val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

            // در این بخش بررسی میشود که مجوز از قبل وجود دارد یا نه
            if (cameraPermission != PackageManager.PERMISSION_GRANTED)
                requestCameraPermission()
            else
                toast("مجوز قبلا دریافت شده")

        }
        
        }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun requestCameraPermission() {

        // در اینجا بررسی میشود که آیا کاربر یک بار مجوز را رد کرده است یا نه
        // اگر رد کرده باشد توضیحات بیشتر در قالب Alert Dialog به او نمایش داده میشود
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            )
        )
            AlertDialog.Builder(this)
                .setTitle("درخواست مجوز")
                .setMessage("برای دسترسی به دوربین باید مجوز را تایید کنید")
                .setCancelable(false)
                .setPositiveButton("موافقم") { _, _ ->
                    reqPermission()
                }
                .setNegativeButton("لغو") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .create()
                .show()
        else
            reqPermission()

    }

    private fun reqPermission() {

        // در بخش arrayOf میتوانیم چندین مجوز را درخواست کنیم
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            cameraRequestCode
        )

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        // در اینجا بررسی میشود که کد مجوز همان چیزی باشد که مد نظر ماست
        if (requestCode == cameraRequestCode) {

            // در این بخش بررسی میشود که آیا مجوز تایید شده یا رد شده است
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                toast("مجوز تایید شد")

            } else {

                toast("مجوز رد شد")

            }

        } else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}
