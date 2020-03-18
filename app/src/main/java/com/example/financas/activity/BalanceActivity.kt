package com.example.financas.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
        init()
    }

    private fun init() {
        // Substituimos o Action Bar pelo Toolbar
        setSupportActionBar(toolbar)
        // Habilitamos o Menu Voltar no Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Buscar Movimentos Salvos no Banco de Dados
        storage.getMoviments()?.let {
            balance.text = storage.getMoviments()?.getResume()
            //Havendo Movimento Salvos irá preencher o nosso RecyclerView (Lista de Movimentos)
            balances.apply {
                layoutManager = LinearLayoutManager(this@BalanceActivity)
                // Criamos o Adapter para preencher nosso RecyclerView
                adapter = BalanceAdapter(it) {
                    // Parte responsável por obter o item a qual foi clicado no RecyclerView
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val REQ_CODE = 100
        const val RESULT_DATA = "RESULT_DATA"

        fun newIntent(context: Context) = Intent(context, BalanceActivity::class.java)
    }

}
