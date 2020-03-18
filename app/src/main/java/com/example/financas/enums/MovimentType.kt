package com.example.financas.enums

import com.example.financas.R

enum class MovimentType(val value: String, val image: Int) {
    IN("Entrada", R.drawable.ic_in),
    OUT("Saída", R.drawable.ic_out)
}