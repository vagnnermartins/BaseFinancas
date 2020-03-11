package com.example.financas.extensions

import java.text.DecimalFormat
import java.text.NumberFormat

fun Double.getFormattedCurrency(): String {
    val formatter = NumberFormat.getCurrencyInstance() as DecimalFormat
    val symbols = formatter.decimalFormatSymbols
    symbols.currencySymbol = "€"
    symbols.groupingSeparator = '.'
    symbols.decimalSeparator = ','
    formatter.decimalFormatSymbols = symbols
    return formatter.format(this)
}