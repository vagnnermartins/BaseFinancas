package com.example.financas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.financas.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(),View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buttonOpen.setOnClickListener (this)

    }

    override fun onClick (view : View) {

        if (view.id == R.id.buttonOpen) {
            handleName()
        }
    }
    private fun handleName(){

        val name : String = userName.text.toString()

        if (name == ""){
            Toast.makeText(this, "Por favor informe o seu nome!", Toast.LENGTH_LONG).show()

        }else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
