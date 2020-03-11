package com.example.financas.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financas.R
import com.example.financas.adapter.BalanceAdapter
import com.example.financas.extensions.getResume
import com.example.financas.storage.FinancasStorage
import kotlinx.android.synthetic.main.activity_balance.*

class BalanceActivity : AppCompatActivity() {

    private val storage: FinancasStorage by lazy { FinancasStorage(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)

        storage.getMoviments()?.let {
            balance.text = storage.getMoviments()?.getResume()
            balances.apply {
                layoutManager = LinearLayoutManager(this@BalanceActivity)
                adapter = BalanceAdapter(it) {
                    val result = Intent()
                    result.putExtra(RESULT_DATA, it)
                    setResult(Activity.RESULT_OK, result)
                    finish()
                }
            }
        } ?: run {
            //TODO EXIBIR MENSAGEM DE SEM MOVIMENTOS
        }

    }

    companion object {

        const val REQ_CODE = 100
        const val RESULT_DATA = "RESULT_DATA"

        fun newIntent(context: Context) = Intent(context, BalanceActivity::class.java)
    }

}
