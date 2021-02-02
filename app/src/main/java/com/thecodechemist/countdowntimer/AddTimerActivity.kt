package com.thecodechemist.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTimerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_timer)

        val btnSaveTimer = findViewById<Button>(R.id.btnSaveTimer)
        btnSaveTimer.setOnClickListener(clickListener)

    }

    fun validateEvent(): Boolean {
        val eventName = findViewById<EditText>(R.id.etEventName).text.toString()
        val eventDate = findViewById<EditText>(R.id.etEventDate).text.toString()
        val eventTime = findViewById<EditText>(R.id.etEventTime).text.toString()

        val validator = TimerValidator()
        var dataIsValid: Boolean = validator.validateData(this, eventName, eventDate, eventTime)

        Toast.makeText(this, dataIsValid.toString(), Toast.LENGTH_SHORT).show()
        return dataIsValid
    }

    val clickListener = View.OnClickListener { view ->

        when(view.getId()) {
            R.id.btnSaveTimer -> validateEvent()
        }
    }
}