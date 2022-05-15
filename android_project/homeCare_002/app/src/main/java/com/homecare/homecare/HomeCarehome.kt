package com.homecare.homecare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeCarehome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_carehome)
        val btn_offerservices = findViewById<Button>(R.id.btn_offerservices);

        btn_offerservices.setOnClickListener{
            val intent = Intent(this,HomeCarerg::class.java)
            startActivity(intent)

        }
    }
}