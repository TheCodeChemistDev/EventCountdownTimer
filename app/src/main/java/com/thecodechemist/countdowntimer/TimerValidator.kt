package com.thecodechemist.countdowntimer

import android.content.Context
import android.icu.number.IntegerWidth
import android.util.Log
import android.widget.Toast
import java.time.LocalDate

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

                    //Check the day
                    //Warning: Does not currently support leap years
                    var enteredDay = eventDate.substring(0, 2)
                    var enteredDayInt = Integer.parseInt(enteredDay)
                    if(enteredDayInt < 1 || enteredDayInt > maxDays) {
                        Toast.makeText(context,  "Day out of bounds", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }
            }
        }
        return true
    }

    fun validateEventTime(context: Context, eventTime: String): Boolean {

        //Check if a time has been entered
        if(!eventTime.equals("")) {
            //Check the input is the expected length
            if(eventTime.length != 5 || eventTime.substring(2,3) != ".") {
                Toast.makeText(context,  "Please use the correct time format", Toast.LENGTH_SHORT).show()
                return false
            } else {
                //Check the Hours entered
                var enteredHour = eventTime.substring(0,2)
                var enteredHourInt = Integer.parseInt(enteredHour)
                if(enteredHourInt < 0 || enteredHourInt > 23) {
                    Toast.makeText(context,  "Hours out of bounds", Toast.LENGTH_SHORT).show()
                    return false
                } else {
                    //Check the Minutes entered
                    var enteredMinute = eventTime.substring(3,5)
                    var enteredMinuteInt = Integer.parseInt(enteredMinute)
                    if(enteredMinuteInt < 0 || enteredMinuteInt > 59) {
                        Toast.makeText(context,  "Minutes out of bounds", Toast.LENGTH_SHORT).show()
                        return false
                    } else {
                        return true
                    }
                }
            }

        } else {
            return true
        }
    }
}