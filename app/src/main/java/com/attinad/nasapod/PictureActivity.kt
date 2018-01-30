package com.attinad.nasapod

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picture.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.net.URL


class PictureActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)


        //JobUtil().scheduleJob(this);

        val today_date = PODMemory().getToayDate()
        val isDataAvail = PODMemory().checkIfDataExist(this,today_date)
        if(isDataAvail){
            val podObj = PODMemory().getPodData(this,today_date)
            updateUI(podObj)
        }else{
            progressBar.visibility = View.VISIBLE
            fetchDataFromWeb()
        }


    }

    private fun fetchDataFromWeb(){
        doAsync {
            val url = URL("https://apod.nasa.gov/apod/astropix.html")
            val document = Jsoup.parse(url, 5000)
            val imageElement = document.select("img").first()
            val imageUrl = imageElement.absUrl("src")  //image url
            val explanation = StringBuilder()
            val paragraphs = document.select("p")
            val aboutPod = StringBuilder()
            val datePod = StringBuilder()
            var count = 0
            var isFinished = false;
            for (p in paragraphs){
                if(!isFinished){
                    if(count == 0){
                        aboutPod.append(p.text());
                    }else if(count == 1){
                        datePod.append(p.text());
                    }else{
                        if(p.text().startsWith("Tomorrow's picture:"))
                            isFinished = true

                        else if(!p.text().startsWith("Authors & editors:")){
                            explanation.append(p.text());
                        }

                    }
                    count+=1
                }
            }


            val podTitleelement = document.select("b").first()
            val podTitle  = podTitleelement.text()
            val podObj = PoDObject(aboutPod.toString(),imageUrl,podTitle,datePod.toString(),explanation.toString())
            PODMemory().saveInfo(applicationContext,datePod.toString(),podObj)
            uiThread {
                updateUI(podObj)
            }


        }

    }

    private fun updateUI(podObj: PoDObject?) {

            try {

                titleTxt.text = "Astronomy Picture of the Day"
                aboutPodTxt.text = podObj!!.podAbout
                datePodTxt.text = podObj!!.podDate

                Picasso.with(applicationContext)
                        .load(podObj!!.podUrl)
                        .into(nasapod,object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                progressBar.visibility = View.GONE
                            }

                            override fun onError() {

                            }
                        })
                titlePodTxt.text = podObj!!.podTitle
                imageDescription.text = podObj!!.podDescription;
            } catch (ex: NullPointerException) {
                ex.printStackTrace()
            }



    }

}
