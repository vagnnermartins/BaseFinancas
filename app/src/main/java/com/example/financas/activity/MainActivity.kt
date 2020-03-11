package com.example.financas.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.entity.MovimentEntity
import com.example.financas.enums.MovimentType
import com.example.financas.extensions.getResume
import com.example.financas.extensions.getValue
import com.example.financas.extensions.isNotEmpty
import com.example.financas.extensions.toast
import com.example.financas.storage.FinancasStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val storage by lazy { FinancasStorage(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        loadResume()
        buttonSave.setOnClickListener { save() }
        buttonClear.setOnClickListener { storage.clearMoviments() }
    }

    private fun save() {
        if (!isValid()) {
            toast(R.string.insira_dados_corretamente)
        } else {
            MovimentEntity(
                editName.getValue(),
                editDescription.getValue(),
                getMovimentType(),
                getTypedValue()
            ).apply {
                storage.addMovimento(this)
                toast(R.string.Movimento_adction_secess)
            }
        }
        loadResume()
        clear()
    }

    private fun getMovimentType(): MovimentType = when (editType.getValue()) {
        MovimentType.IN.name -> MovimentType.IN
        MovimentType.OUT.name -> MovimentType.OUT
        else -> MovimentType.OUT
    }

    private fun getTypedValue(): Double = try {
        editValue.getValue().toDouble()
    } catch (exception: Exception) {
        0.0
    }

    private fun isValid(): Boolean = editName.isNotEmpty()
            || editType.isNotEmpty()
            || editObrigation.text.toString() == "IN"
            || editObrigation.text.toString() == "OUT"
            || editValue.isNotEmpty()

    private fun clear() {
        editName.text.clear()
        editDescription.text.clear()
        editType.text.clear()
        editValue.text.clear()

    }

    private fun loadResume() {
        textResume.text = storage.getMoviments()?.getResume()
    }
}