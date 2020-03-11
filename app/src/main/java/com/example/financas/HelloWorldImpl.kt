package com.example.financas

import android.util.Log

class HelloWorldImpl(override val vulue: String) : HelloWorld<String> {


    override fun setWorld(world: String) {
        Log.d("TAG", "Estabelecendo o tipo: ${vulue}")

    }

    override fun getWorld(): String {
        Log.d("TAG", "O mundo é do tipo: ${vulue}")
        return vulue
    }

}