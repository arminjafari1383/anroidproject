package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore.Video.Media
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
    private var mediaPlayer:MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fabStart.setOnClickListener { startMusic() }
        binding.fabPause.setOnClickListener { pauseMusic() }
        binding.fabStop.setOnClickListener { stopMusic() }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if( fromUser ) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        }

    private fun startMusic() {
        if (mediaPlayer == null ) {
            val uri = "https://dl.mifa-music.ir/Music1/08/Erfan%20Tahmasbi%20-%20Harchi%20Daram.mp3"
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioAttributes(
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build()
            )
            mediaPlayer?.setDataSource(uri)
            mediaPlayer?.prepare()
            mediaPlayer?.setOnCompletionListener {
                stopMusic()
            }
            initSeekBar()
        }
        mediaPlayer?.start()
    }

    private fun pauseMusic() {mediaPlayer?.pause()}

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun initSeekBar(){
        binding.seekBar.max = mediaPlayer?.duration ?: 100
        val handler = Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this,1000)
                }
                catch (e:Exception){
                    binding.seekBar.progress = 0
                }

            }

        },0)
    }

    override fun onStop() {
        super.onStop()
        stopMusic()
    }

}


