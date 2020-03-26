package com.example.financas.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormat(pattern: String = "dd/MM/yyyy 'às' HH:mm:ss") =
    SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))