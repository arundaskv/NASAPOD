package com.attinad.nasapod

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picture.*
import android.app.ActivityManager
import android.content.Context
import android.os.Binder
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.content.ComponentName
import android.app.job.JobScheduler
class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(
                JobInfo.Builder(1, ComponentName(this, WallpaperService::class.java!!))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(3000)
                .build()
        )

        val urlUtil = NASAUrlUtility();
        val url = urlUtil.getImageUrlFromNASAPage();
        Picasso.with(this)
                .load(url)
                .into(nasapod);

        val wallpaperUtility = WallpaperUtility();
        wallpaperUtility.setAsDefaultWallpaper(this,url);
    }

}
