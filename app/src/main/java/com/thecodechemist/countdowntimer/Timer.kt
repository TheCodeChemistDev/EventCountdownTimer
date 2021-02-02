package com.thecodechemist.countdowntimer

class Timer(name: String, date: String, time: String) {

    val eventName = name
    val eventDate = date
    val eventTime = time

    fun getName(): String {
        return eventName
    }

    fun getDate(): String {
        return eventDate
    }

    fun getTime(): String {
        return eventTime
    }
}

