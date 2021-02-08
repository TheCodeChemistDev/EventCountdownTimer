package com.thecodechemist.countdowntimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    //For Coroutine
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv: RecyclerView = findViewById(R.id.rvTimers)
        rv.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = layoutManager

        //Get the list of existing timers from the database
        job = Job()
        launch {
            this@MainActivity.let {
                val timers = AppDatabase(it).getTimerDao().getAll()
                rv.adapter = TimerAdapter(timers)
            }
        }

        val fabAddTimer = findViewById<FloatingActionButton>(R.id.fabAddTimer)
        fabAddTimer.setOnClickListener(clickListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


    val clickListener = View.OnClickListener { view ->

        when(view.getId()) {
            R.id.fabAddTimer -> goToAddTimer()
        }
    }

    fun goToAddTimer() {
        //Redirect to the Add Timer Activity
        val intent = Intent(this, AddTimerActivity::class.java)
        startActivity(intent)
    }

    fun updateTimers(timer: Timer) {


    }
    
}