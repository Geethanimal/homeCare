package com.homeCare.lk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeCareLoading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.sleep(2000)
        val intent = Intent(this,HomeCarehome::class.java)
        startActivity(intent)
    }
}