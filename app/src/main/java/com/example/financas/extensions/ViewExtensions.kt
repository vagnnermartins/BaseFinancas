package com.example.financas.extensions

import android.widget.EditText

fun EditText.getValue(): String = text.toString()

fun EditText.isNotEmpty() = text.toString().isNotEmpty()