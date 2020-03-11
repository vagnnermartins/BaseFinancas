package com.example.financas.extensions

import com.example.financas.entity.MovimentEntity
import com.example.financas.enums.MovimentType

fun List<MovimentEntity>.getResume(): String {
    var balance = 0.0
    map {
        if (it.type == MovimentType.IN) {
            balance += it.value
        } else {
            balance -= it.value
        }
    }
    return balance.getFormattedCurrency()
}