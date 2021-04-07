package com.thecodechemist.countdowntimer

import android.content.Context
import android.widget.Toast

class TimerValidator {

    fun validateData(context: Context, eventName: String, eventDate: String, eventTime: String): Boolean {

        val eventNameIsValid = validateEventName(context, eventName)
        val eventDateIsValid = validateEventDate(context, eventDate)
        val eventTimeIsValid = validateEventTime(context, eventTime)

        var dataIsValid = false
        if(eventNameIsValid && eventDateIsValid && eventTimeIsValid) {
            dataIsValid = true
        }
        return dataIsValid
    }

    private fun validateEventName(context: Context, eventName: String): Boolean {

        //Check if an event name has been entered
        if(eventName == "") {
            Toast.makeText(context,  "Event name cannot be blank!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateEventDate(context: Context, eventDate: String): Boolean {

        //TODO: Attach error messages to the appropriate EditText fields using EditText.error

        //Check an event date has been entered and is of expected length
        if(eventDate == "" || eventDate.length != 10) {
            Toast.makeText(context,  "Please use the correct date format", Toast.LENGTH_SHORT).show()
            return false
        } else {
            //Check the entered date is valid...
            //Check slashes are in correct place
            if(eventDate.substring(2, 3) != "/" || eventDate.substring(5, 6) != "/") {
                Toast.makeText(context,  "Please use the correct date format", Toast.LENGTH_SHORT).show()
                return false
            } else {
                //Check the day/month/year are within expected bounds
                //Check the year
                val enteredYear = eventDate.substring(6, 10)
                val enteredYearInt = Integer.parseInt(enteredYear)
                if(enteredYearInt < 1900 || enteredYearInt > 2100) {
                    Toast.makeText(context,  "Year out of bounds", Toast.LENGTH_SHORT).show()
                    return false
                }

                //Check the month
                val enteredMonth = eventDate.substring(3, 5)
                val enteredMonthInt = Integer.parseInt(enteredMonth)
                if(enteredMonthInt < 1 || enteredMonthInt > 12) {
                    Toast.makeText(context,  "Month out of bounds", Toast.LENGTH_SHORT).show()
                    return false
                } else {
                    var maxDays = 0
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

                    //Check the day
                    //Warning: Does not currently support leap years
                    val enteredDay = eventDate.substring(0, 2)
                    val enteredDayInt = Integer.parseInt(enteredDay)
                    if(enteredDayInt < 1 || enteredDayInt > maxDays) {
                        Toast.makeText(context,  "Day out of bounds", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun validateEventTime(context: Context, eventTime: String): Boolean {

        //Check if a time has been entered
        if(eventTime != "") {
            //Check the input is the expected length
            if(eventTime.length != 5 || eventTime.substring(2,3) != ".") {
                Toast.makeText(context,  "Please use the correct time format", Toast.LENGTH_SHORT).show()
                return false
            } else {
                //Check the Hours entered
                val enteredHour = eventTime.substring(0,2)
                val enteredHourInt = Integer.parseInt(enteredHour)
                return if(enteredHourInt < 0 || enteredHourInt > 23) {
                    Toast.makeText(context,  "Hours out of bounds", Toast.LENGTH_SHORT).show()
                    false
                } else {
                    //Check the Minutes entered
                    val enteredMinute = eventTime.substring(3,5)
                    val enteredMinuteInt = Integer.parseInt(enteredMinute)
                    if(enteredMinuteInt < 0 || enteredMinuteInt > 59) {
                        Toast.makeText(context,  "Minutes out of bounds", Toast.LENGTH_SHORT).show()
                        false
                    } else {
                        true
                    }
                }
            }

        } else {
            return true
        }
    }
}