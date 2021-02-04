package com.thecodechemist.countdowntimer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timer")
    fun getAll(): List<Timer>

    @Insert
    fun addTimer(timer: Timer)

    @Delete
    fun delete(timer: Timer)


}