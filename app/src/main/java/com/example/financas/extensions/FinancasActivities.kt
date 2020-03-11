package com.example.financas.extensions

import android.widget.Toast
import com.example.financas.activity.MainActivity

fun MainActivity.toast(text: Int) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()