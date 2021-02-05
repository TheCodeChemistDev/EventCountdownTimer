package com.thecodechemist.countdowntimer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimerDao {

    @Query("SELECT * FROM Timer")
    suspend fun getAll(): List<Timer>

    @Insert
    suspend fun addTimer(timer: Timer)

    @Delete
    suspend fun delete(timer: Timer)


}