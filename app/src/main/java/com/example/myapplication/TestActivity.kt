package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("id",0)
        val sleep = intent.getBooleanExtra("sleep",false)
        binding.btnTest2.setOnClickListener{
            Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
        }
    }
}