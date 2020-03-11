package com.example.financas.extensions

import com.example.financas.entity.MovimentEntity
import com.example.financas.enums.MovimentType

fun List<MovimentEntity>.getResume(): String {
    var resume = ""
    var balance = 0.0
    map {
        resume += it.toString() + "\n\n\n"

        if (it.type == MovimentType.IN) {
            balance += it.value
        } else {
            balance -= it.value
        }
    }

    resume += "TOTAL: ${balance.getFormattedCurrency()}"
    return resume
}