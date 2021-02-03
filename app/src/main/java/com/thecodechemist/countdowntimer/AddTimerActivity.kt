package com.thecodechemist.countdowntimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTimerActivity : AppCompatActivity() {

    lateinit var etEventName: EditText
    lateinit var etEventDate: EditText
    lateinit var etEventTime: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_timer)

        etEventName = findViewById(R.id.etEventName)
        etEventDate = findViewById(R.id.etEventDate)
        etEventTime = findViewById(R.id.etEventTime)
        val btnSaveTimer = findViewById<Button>(R.id.btnSaveTimer)
        btnSaveTimer.setOnClickListener(clickListener)

    }

    fun validateEvent(): Boolean {
        val eventName = etEventName.text.toString()
        val eventDate = etEventDate.text.toString()
        val eventTime = etEventTime.text.toString()
        val validator = TimerValidator()
        val dataIsValid: Boolean = validator.validateData(this, eventName, eventDate, eventTime)
        return dataIsValid
    }

    fun createTimer() {
        val eventName = etEventName.text.toString()
        val eventDate = etEventDate.text.toString()
        val eventTime = etEventTime.text.toString()

        val timer = Timer(eventName, eventDate, eventTime)

        Log.e("Name", timer.name)
        Log.e("Date", timer.date)
        Log.e("Time", timer.time)

        val intent = Intent()

    }

    val clickListener = View.OnClickListener { view ->

        when(view.getId()) {
            R.id.btnSaveTimer -> if(validateEvent()) { createTimer() }
        }
    }
}