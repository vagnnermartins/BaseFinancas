package com.example.financas.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financas.R
import com.example.financas.balance.BalanceActivity
import com.example.financas.bottomsheet.MovimentTypeDialog
import com.example.financas.entity.MovimentEntity
import com.example.financas.enums.MovimentType
import com.example.financas.extensions.getValue
import com.example.financas.extensions.isNotEmpty
import com.example.financas.extensions.toast
import com.example.financas.storage.FinancasStorage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovimentTypeDialog.MovimentTypeListener {

    private var movimentType: MovimentType? = null

    private val storage by lazy { FinancasStorage(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        buttonSave.setOnClickListener { save() }
        buttonClear.setOnClickListener { storage.clearMoviments() }
        buttonBalance.setOnClickListener {
            startActivityForResult(
                BalanceActivity.newIntent(MainActivity@ this),
                BalanceActivity.REQ_CODE
            )
        }
        editType.setOnClickListener {
            // Responsável por abrir o menu no estilo BottomSheetDialog
            MovimentTypeDialog.newInstance().apply {
                listener = this@MainActivity
                show(supportFragmentManager, MovimentTypeDialog.TAG)
            }
        }
    }

    private fun save() {
        if (!isValid()) {
            toast(R.string.insira_dados_corretamente)
        } else {
            MovimentEntity(
                editName.getValue(),
                editDescription.getValue(),
                movimentType!!,
                getTypedValue()
            ).apply {
                storage.addMovimento(this)
                toast(R.string.Movimento_adction_secess)
            }
        }
        clear()
    }

    private fun getTypedValue(): Double = try {
        editValue.getValue().toDouble()
    } catch (exception: Exception) {
        0.0
    }

    private fun isValid(): Boolean = editName.isNotEmpty()
            || movimentType != null
            || editValue.isNotEmpty()

    private fun clear() {
        editName.text.clear()
        editDescription.text.clear()
        editType.setText(R.string.insert_moviment_type)
        editValue.text.clear()
    }

    //Responsável por verificar de onde a Activity está retornando
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                BalanceActivity.REQ_CODE -> {
                    val moviment =
                        data?.getSerializableExtra(BalanceActivity.RESULT_DATA) as MovimentEntity
                    editName.setText(moviment.name)
                    editDescription.setText(moviment.description)
                    editType.setText(moviment.type.name)
                    editValue.setText(moviment.value.toString())
                }
            }
        }
    }

    override fun onMovimentSelected(movimentType: MovimentType) {
        this.movimentType = movimentType
        editType.text = movimentType.value
    }

}