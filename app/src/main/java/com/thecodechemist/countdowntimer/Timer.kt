package com.thecodechemist.countdowntimer

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import java.lang.StringBuilder

@Parcelize
data class Timer(
        val name: String,
        val date: String,
        val time: String): Parcelable {


        override fun toString(): String {
                val sb: StringBuilder = StringBuilder()
                sb.append("Event Name: ")
                sb.append(name)
                sb.append(" | Event Date: ")
                sb.append(date)
                sb.append(" | Event Time: ")
                sb.append(time)

                return sb.toString()
        }
}

