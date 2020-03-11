package com.example.financas.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.financas.entity.MovimentEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

private const val PREF_NAME = "FINANCAS"
private const val MOVIMENTS = "MOVIMENTS"

class FinancasStorage(context: Context) {

    private val moshi: Moshi = Moshi.Builder().build()

    private val adapter = moshi.adapter<List<MovimentEntity>>(
        Types.newParameterizedType(List::class.java, MovimentEntity::class.java)
    )

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun addMovimento(movimentoEntity: MovimentEntity) {
        val moviments = getMoviments()!!
        moviments.add(movimentoEntity)
        sharedPref.edit().putString(MOVIMENTS, adapter.toJson(moviments)).apply()
    }

    fun getMoviments(): MutableList<MovimentEntity>? {
        return if (hasMoviments()) {
            adapter.fromJson(sharedPref.getString(MOVIMENTS, "[]"))?.toMutableList()
        } else {
            mutableListOf()
        }
    }

    fun clearMoviments() = sharedPref.edit().remove(MOVIMENTS).apply()

    fun hasMoviments(): Boolean = sharedPref.contains(MOVIMENTS)
}