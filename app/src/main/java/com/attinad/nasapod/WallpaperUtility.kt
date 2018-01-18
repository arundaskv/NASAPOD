package com.attinad.nasapod

import android.app.WallpaperManager
import android.content.Context
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException

/**
 * Created by Arun.Das on 22-11-2017.
 */
class WallpaperUtility {

    fun setAsDefaultWallpaper (context : Context, url : String)= {
        doAsync {
            val result = Picasso.with(context).load(url).get()
            val wallpaperManager = WallpaperManager.getInstance(context)
            uiThread {
                try {
                    wallpaperManager.setBitmap(result)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        }
    }


}