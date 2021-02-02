package com.thecodechemist.countdowntimer

class TimerValidator {

    fun validateData(eventName: String, eventDate: String, eventTime: String): Boolean {

        var dataIsValid = true
        if(eventName.equals("") || eventName == null) {
            dataIsValid = false
        }

        if(eventDate.equals("")) {
            dataIsValid = false
        }

        if(eventTime.equals("")) {
            dataIsValid = false
        }

        return dataIsValid
    }
}