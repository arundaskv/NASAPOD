package com.attinad.nasapod

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = Timer("schedule", true);
        timer.schedule(3000) {
            startNewActivity();
        }


    }

    fun startNewActivity(){
        this.finish()
        val intent = Intent(this,PictureActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }
}
