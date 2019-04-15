package com.nwar.individual.stopwatch

class TimeFormat(var time : Int) {
    fun format() : String {

        val milSec = time%100
        time = time/100
        val sec = time%60
        time = time/60
        val min = time%60
        time = time/60
        val hour = time%60

        var formatString : String
        formatString = if(hour==0) "" else formatNative(hour) + ":"
        formatString += formatNative(min) + ":"
        formatString += formatNative(sec) + ":"
        formatString += formatNative(milSec)
        return formatString
    }

    private fun formatNative(time: Int) : String {
        when(time){
            in 0..9 -> return "0"+time.toString()
            else -> return time.toString()
        }
    }
}