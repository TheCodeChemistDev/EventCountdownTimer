package com.thecodechemist.countdowntimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.thecodechemist.countdowntimer.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddTimerActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    private lateinit var etEventName: EditText
    private lateinit var etEventDate: EditText
    private lateinit var etEventTime: EditText

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

    private fun validateEvent(): Boolean {
        val eventName = etEventName.text.toString()
        val eventDate = etEventDate.text.toString()
        val eventTime = etEventTime.text.toString()
        val validator = TimerValidator()
        return validator.validateData(this, eventName, eventDate, eventTime)
    }

    private fun createTimer() {
        val eventName = etEventName.text.toString()
        val eventDate = etEventDate.text.toString()
        var eventTime = etEventTime.text.toString()
        if(eventTime == "") {
            eventTime = "00.00"
        }

        launch {
            val timer = Timer(eventName, eventDate, eventTime)
            this@AddTimerActivity.let {
                AppDatabase(it).getTimerDao().addTimer(timer)
                Toast.makeText(this@AddTimerActivity, "Timer Saved!", Toast.LENGTH_SHORT).show()
                val i = Intent(this@AddTimerActivity, MainActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
            }

        }
    }



    private val clickListener = View.OnClickListener { view ->

        when(view.id) {
            R.id.btnSaveTimer -> if(validateEvent()) { createTimer() }
        }
    }

}