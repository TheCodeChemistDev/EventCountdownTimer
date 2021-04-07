package com.thecodechemist.countdowntimer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thecodechemist.countdowntimer.db.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    //For Coroutine
    private lateinit var job: Job
    private lateinit var timers: List<Timer>
    private lateinit var hUiUpdate: Handler
    private lateinit var rUiUpdate: Runnable
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
                timers = AppDatabase(it).getTimerDao().getAll()
                rv.adapter = TimerAdapter(timers)
            }
        }

        //Setup for refreshing UI every 1 second
        hUiUpdate = Handler()
        rUiUpdate = Runnable {
            rv.adapter?.notifyDataSetChanged()
            hUiUpdate.postDelayed(rUiUpdate, 1000)
        }
        hUiUpdate.post(rUiUpdate)

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