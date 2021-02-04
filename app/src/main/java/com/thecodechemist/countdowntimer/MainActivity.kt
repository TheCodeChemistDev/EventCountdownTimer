package com.thecodechemist.countdowntimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var timerList = ArrayList<Timer>()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {

            val timer: Timer = data?.getParcelableExtra("NewTimer")!!
            if (timer != null) {
                timerList.add(timer)
            } else {
                Log.e("onActivityResult Error", "Received null for timer")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabAddTimer = findViewById<FloatingActionButton>(R.id.fabAddTimer)

        fabAddTimer.setOnClickListener(clickListener)
    }


    val clickListener = View.OnClickListener { view ->

        when(view.getId()) {
            R.id.fabAddTimer -> goToAddTimer()
        }
    }

    fun goToAddTimer() {
        val intent = Intent(this, AddTimerActivity::class.java)
        startActivityForResult(intent, 1);
    }
    
}