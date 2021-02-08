package com.thecodechemist.countdowntimer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.timer_layout.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.TemporalAmount
import java.util.*
import kotlin.concurrent.timer

class TimerAdapter(private val timers: List<Timer>) : RecyclerView.Adapter<TimerAdapter.TimerViewHolder>() {

    class TimerViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder {
        return TimerViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.timer_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) {
        //Set the event details
        val sb: StringBuilder = StringBuilder()
        sb.append(timers[position].name)
        sb.append(" on ")
        sb.append(timers[position].date)
        sb.append(" @ ")
        sb.append(timers[position].time)
        holder.view.tvEventDetails.text = sb.toString()

        //Set the time remaining...
        val sdf: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

        //Event Time
        val day: String = timers[position].date.substring(0, 2)
        val month: String = timers[position].date.substring(3, 5)
        val year: String = timers[position].date.substring(6, 10)
        val hour: String = timers[position].time.substring(0, 2)
        val minute: String = timers[position].time.substring(3, 5)
        val eventDateTime = sdf.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute + ":00")

        //Current Time
        val now = LocalDateTime.now()
        val currentDay = now.dayOfMonth
        val currentMonth = now.monthValue
        val currentYear = now.year
        val currentHour = now.hour
        val currentMinute = now.minute
        val currentSecond = now.second
        val currentDateTime = sdf.parse(Integer.toString(currentDay) + "/" + currentMonth + "/" + currentYear + " " + currentHour + ":" + currentMinute + ":" + currentSecond)

        //Calculate the difference
        var timeDiffInMillis = Math.abs(eventDateTime.time - currentDateTime.time)
        var timeDiffInSecs = timeDiffInMillis / 1000
        val daysDiff = (timeDiffInMillis / 1000) / 86400
        val hoursDiff = ((timeDiffInMillis / 1000) % 86400) / 3600
        val minutesDiff = (((timeDiffInMillis / 1000) % 86400) % 3600) / 60
        val secondsDiff = (((timeDiffInMillis / 1000) % 86400) % 3600) % 60
        Log.e("Total Time Diff in Secs", "" + timeDiffInSecs)
        Log.e("Diff in Days", "" + daysDiff)
        Log.e("Diff in Hours", "" + hoursDiff)
        Log.e("Diff in Minutes", "" + minutesDiff)
        Log.e("Diff in Secs", "" + secondsDiff)

        //Dislay Time Remaining
        val sb2: StringBuilder = StringBuilder()
        sb2.append(daysDiff)
        sb2.append(" Days, ")
        sb2.append(hoursDiff)
        sb2.append(" Hours, ")
        sb2.append(minutesDiff)
        sb2.append(" Minutes, and ")
        sb2.append(secondsDiff)
        sb2.append(" Seconds Remaining...")
        holder.view.tvTimeRemaining.text = sb2.toString()
    }

    override fun getItemCount(): Int = timers.size


}