package com.example.financas.entity

import com.example.financas.enums.MovimentType
import com.example.financas.extensions.getFormattedCurrency
import java.io.Serializable
import java.util.*

class MovimentEntity(
    val name: String,
    val description: String?,
    val type: MovimentType,
    val value: Double,
    val date: Long = Date().time
) : Serializable {
    override fun toString(): String {
        return "Nome $name\n" +
                "Descrição  $description\n" +
                "Tipo ${type.value}\n" +
                "Valor  ${value.getFormattedCurrency()}\n" +
                "Data: $date"
    }
}
