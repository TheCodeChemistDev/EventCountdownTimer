package com.thecodechemist.countdowntimer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.timer_layout.view.*
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
        val sb: StringBuilder = StringBuilder()
        sb.append(timers[position].name)
        sb.append(" on ")
        sb.append(timers[position].date)
        sb.append(" @ ")
        sb.append(timers[position].time)

        holder.view.tvEventDetails.text = sb.toString()

        holder.view.tvTimeRemaining.text = "X Days, X Hours, X Minutes and X Seconds"
    }

    override fun getItemCount(): Int = timers.size

}