package com.vikas.fitbit.utils

import android.annotation.SuppressLint
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import java.text.SimpleDateFormat
import java.util.*


object Utils {


    @SuppressLint("SimpleDateFormat")
    fun getDate(position: Int):String {
        val c = Calendar.getInstance()

        c.add(Calendar.DATE, position)
        println("Current time => " + c.time)
        val df = SimpleDateFormat("EE, dd-MMM")
        val formattedDate = df.format(c.time)

        return when(position) {
            0 -> {
                "TODAY"
            }
            -1 -> {
                "YESTERDAY"
            }
            else -> {
                formattedDate.toString()
            }
        }

    }


}