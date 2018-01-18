package com.attinad.nasapod

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import org.jetbrains.anko.toast

/**
 * Created by Arun.Das on 22-11-2017.
 */
public class WallpaperService : JobService() {
    override fun onStopJob(p0: JobParameters?): Boolean {
        toast("Job onStopJob")
        return true;
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        toast("Job Started")
        return true;
    }


}