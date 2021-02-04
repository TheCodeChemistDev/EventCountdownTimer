package com.thecodechemist.countdowntimer

import android.os.Parcelable
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.lang.StringBuilder

@Entity
data class Timer(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "EventName") val name: String,
        @ColumnInfo(name = "EventDate") val date: String,
        @ColumnInfo(name = "EventTime") val time: String) {

        constructor(name: String, date: String, time: String) : this(0, name, date, time)


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

