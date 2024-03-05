package com.example.contants_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.ContactsActivity

class SplashActivity:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(mainLooper).postDelayed({
            startContactsActivity()
        },2000)
    }

    private fun startContactsActivity() {
        startActivity(Intent(this,ContactsActivity::class.java))
        finish()
    }
}
