package com.attinad.nasapod

import android.content.Context
import android.content.ComponentName
import android.app.job.JobInfo
import android.app.job.JobScheduler







/**
 * Created by Arun.Das on 18-01-2018.
 */
class JobUtil {
    fun scheduleJob( context : Context){
        val serviceComponent = ComponentName(context, WallpaperService::class.java)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency(1 * 1000); // wait at least
        builder.setOverrideDeadline(3 * 1000); // maximum delay
        val jobScheduler = context.getSystemService(JobScheduler::class.java)
        jobScheduler.schedule(builder.build())

        /*val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(
                JobInfo.Builder(1, ComponentName(this, WallpaperService::class.java!!))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(3000)
                .build()
        )*/
    }
}