package com.attinad.nasapod

import android.content.Context
import com.google.gson.Gson

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Arun.Das on 30-01-2018.
 */
class PODMemory {
    val PREFS_FILENAME = "com.teamtreehouse.colorsarefun.prefs"
    val POD_DATE = "podDate"
    val POD_TITLE = "podTitle"
    val POD_ABOUT = "podAbout"

    public fun checkIfDataExist(context : Context,today:String):Boolean{
        val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
        val jsonString = prefs!!.getString(today, null)
        if(jsonString!=null){
            return true;
        }
            return false;

    }

    public fun getToayDate(): String {
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy MMMM dd")
        val formattedDate = df.format(c.getTime())
        return formattedDate
    }

    public fun saveInfo(context: Context,key:String,podObj:PoDObject){
        val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
        val editor = prefs!!.edit()
        val jsonString = Gson().toJson(podObj)
        editor.putString(key, jsonString)
        editor.apply()
    }

    public fun getPodData(context: Context,dateStr:String) : PoDObject?{
        val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)
        val podObjString = prefs!!.getString(dateStr, null)

        try{
            val podObj = Gson().fromJson<PoDObject>(podObjString,PoDObject::class.java)
            return podObj
        }catch (e : Exception){
            return null;
        }

    }
}