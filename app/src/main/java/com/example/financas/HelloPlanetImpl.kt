package com.example.financas

import android.util.Log

class HelloPlanetImpl(override val vulue: Int) : HelloWorld<Int> {


    override fun setWorld(world: Int) {
        Log.d("TAG", "Estabelecendo o tipo: ${vulue}")

    }

    override fun getWorld(): Int {
        Log.d("TAG", "O mundo é do tipo: ${vulue}")
        return vulue
    }

}