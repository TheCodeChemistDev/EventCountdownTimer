package com.thecodechemist.countdowntimer

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Timer::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timerDao(): TimerDao

}