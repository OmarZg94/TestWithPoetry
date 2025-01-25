package com.example.testwithpoetry.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "MM/dd/yyy"

fun Long.convertMillisToDate(): String {
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return formatter.format(Date(this))
}