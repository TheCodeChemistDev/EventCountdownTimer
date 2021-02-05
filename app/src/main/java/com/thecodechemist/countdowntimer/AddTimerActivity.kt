package com.thecodechemist.countdowntimer

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddTimerActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    lateinit var etEventName: EditText
    lateinit var etEventDate: EditText
    lateinit var etEventTime: EditText

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_timer)

        job = Job()
        etEventName = findViewById(R.id.etEventName)
        etEventDate = findViewById(R.id.etEventDate)
        etEventTime = findViewById(R.id.etEventTime)
        val btnSaveTimer = findViewById<Button>(R.id.btnSaveTimer)
        btnSaveTimer.setOnClickListener(clickListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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
        var eventTime = etEventTime.text.toString()
        if(eventTime.equals("")) {
            eventTime = "00.00"
        }

        launch {
            val timer = Timer(eventName, eventDate, eventTime)
            this@AddTimerActivity.let {
                AppDatabase(it).getTimerDao().addTimer(timer)
                Toast.makeText(this@AddTimerActivity, "Timer Saved!", Toast.LENGTH_SHORT).show()
            }

        }
    }



    val clickListener = View.OnClickListener { view ->

        when(view.getId()) {
            R.id.btnSaveTimer -> if(validateEvent()) { createTimer() }
        }
    }

}