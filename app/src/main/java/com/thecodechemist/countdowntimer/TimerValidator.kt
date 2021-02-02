package com.thecodechemist.countdowntimer

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.time.LocalDate

class TimerValidator {

    fun validateData(context: Context, eventName: String, eventDate: String, eventTime: String): Boolean {

        val eventNameIsValid = validateEventName(context, eventName)
        val eventDateIsValid = validateEventDate(context, eventDate)



        //Check if a time has been entered, and if so, that the time is valid
        if(!eventTime.equals("")) {

        }
        var dataIsValid = false
        if(eventNameIsValid && eventDateIsValid) {
            dataIsValid = true
        }
        return dataIsValid
    }

    fun validateEventName(context: Context, eventName: String): Boolean {

        //Check if an event name has been entered
        if(eventName.equals("")) {
            Toast.makeText(context,  "Event name cannot be blank!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun validateEventDate(context: Context, eventDate: String): Boolean {

        //Check an event date has been entered and is of expected length
        if(eventDate.equals("") || eventDate.length != 10) {
            Toast.makeText(context,  "Please use the correct date format", Toast.LENGTH_SHORT).show()
            return false
        } else {
            //Check the entered date is valid...
            //Check slashes are in correct place
            if(!eventDate.substring(2, 3).equals("/") || !eventDate.substring(5, 6).equals("/")) {
                Toast.makeText(context,  "Please use the correct date format", Toast.LENGTH_SHORT).show()
                return false
            } else {
                //Check the day/month/year are within expected bounds
                //Check the year
                var enteredYear = eventDate.substring(6, 10)
                var enteredYearInt = Integer.parseInt(enteredYear)
                if(enteredYearInt < 1900 || enteredYearInt > 2100) {
                    Toast.makeText(context,  "Year out of bounds", Toast.LENGTH_SHORT).show()
                    return false
                }

                //Check the month
                var enteredMonth = eventDate.substring(3, 5)
                var enteredMonthInt = Integer.parseInt(enteredMonth)
                if(enteredMonthInt < 1 || enteredMonthInt > 12) {
                    Toast.makeText(context,  "Month out of bounds", Toast.LENGTH_SHORT).show()
                    return false
                } else {
                    var maxDays: Int = 0
                    when(enteredMonthInt) {
                        1 -> maxDays = 31
                        2 -> maxDays = 28
                        3 -> maxDays = 31
                        4 -> maxDays = 30
                        5 -> maxDays = 31
                        6 -> maxDays = 30
                        7 -> maxDays = 31
                        8 -> maxDays = 31
                        9 -> maxDays = 30
                        10 -> maxDays = 31
                        11 -> maxDays = 30
                        12 -> maxDays = 31
                    }

                    var enteredDay = eventDate.substring(0, 2)
                    var enteredDayInt = Integer.parseInt(enteredDay)
                    if(enteredDayInt < 1 || enteredDayInt > maxDays) {
                        Toast.makeText(context,  "Month out of bounds", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }
            }
        }
        return true
    }
}