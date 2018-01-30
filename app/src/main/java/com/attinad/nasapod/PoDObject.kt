package com.attinad.nasapod
import java.io.Serializable
/**
 * Created by Arun.Das on 30-01-2018.
 */
data class PoDObject (val podAbout:String, val podUrl:String,val podTitle:String, val podDate:String,val podDescription:String) : Serializable