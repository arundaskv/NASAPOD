package com.attinad.nasapod

import android.app.WallpaperManager
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picture.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.io.IOException
import java.net.URL

/**
 * Created by Arun.Das on 22-11-2017.
 */
class NASAUrlUtility {


    public fun getImageUrlFromNASAPage(): String {
        val imageUrl = "https://apod.nasa.gov/apod/image/1711/OumuamuaDrawing_ESO_1280.jpg"
        return imageUrl;
    }

    fun init(context:Context,imageView : ImageView, imageTitle: TextView,imageDescription : TextView ){
        doAsync {
            val url = URL("https://apod.nasa.gov/apod/astropix.html")
            val document = Jsoup.parse(url, 5000)
            val imageElement = document.select("img").first()
            val imageUrl = imageElement.absUrl("src")  //image url
            val explanation = StringBuilder()
            val paragraphs = document.select("p")
            for (p in paragraphs)
                explanation.append(p.text());

            val  description = explanation.toString()

            uiThread {
                try {
                    Picasso.with(context)
                            .load(imageUrl)
                            .into(imageView);
                    imageDescription.text = description;

                } catch (ex: NullPointerException) {
                    ex.printStackTrace()
                }
            }

        }

    }

}