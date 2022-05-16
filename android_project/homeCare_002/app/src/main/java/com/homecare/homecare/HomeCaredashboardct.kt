package com.homecare.homecare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeCaredashboardct : AppCompatActivity() {

    private lateinit var btnsignout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_caredashboardct)

        btnsignout = findViewById(R.id.btn_signout)

        btnsignout.setOnClickListener{
            Firebase.auth.signOut()
            var intent = Intent(this,HomeCarelg::class.java)
            startActivity(intent)
        }

    }
}