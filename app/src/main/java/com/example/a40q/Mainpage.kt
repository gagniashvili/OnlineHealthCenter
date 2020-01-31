package com.example.a40q

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.mainpage.*

class Mainpage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        profile.setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }


    }
}