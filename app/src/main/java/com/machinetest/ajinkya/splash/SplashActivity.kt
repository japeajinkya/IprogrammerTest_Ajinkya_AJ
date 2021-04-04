package com.machinetest.ajinkya.splash.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.machinetest.ajinkya.R
import com.machinetest.ajinkya.dashboard.activity.DashboardActivity
import com.machinetest.ajinkya.other.utils.AppUtility
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }


    private fun init() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            finish()
        }, 4000)
    }
}
