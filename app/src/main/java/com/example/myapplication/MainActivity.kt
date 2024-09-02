package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
        if (isInternetAvailable())
        setWebview()
        else
            showErrorDialog()
    }

    private fun showErrorDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("No internet")
        dialog.setMessage("No internet, please connect to internet")
        dialog.setIcon(R.drawable.ic_internet)
        dialog.setPositiveButton("Retry"){_,_,->
            recreate()
        }
        dialog.setNegativeButton("Cancel"){_,_,->
            finish()
        }
        dialog.setNeutralButton("Setting"){_,_,->
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        dialog.create().show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebview() {
        startload()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.digikala.com/")
        binding.webView.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                endLoad()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                endLoad()
            }
        }
    }

    private fun startload() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun endLoad() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onResume() {
        super.onResume()
        if(!isInternetAvailable())
            showErrorDialog()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()){
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun isInternetAvailable():Boolean{
        var result = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when{
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
                val netInfo = connectivityManager.activeNetworkInfo
                result = when(netInfo?.type){
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
        }
        return result
    }
    private fun toast(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
}
