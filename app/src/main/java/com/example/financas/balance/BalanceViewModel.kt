package com.example.financas.balance

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financas.entity.MovimentEntity
import com.example.financas.storage.FinancasStorage

class BalanceViewModel(context: Context) : ViewModel() {

    private val storage = FinancasStorage(context)

    private val mutableLiveData = MutableLiveData<List<MovimentEntity>>()
    val data: LiveData<List<MovimentEntity>> = mutableLiveData

    init {
        loadData(storage.getMoviments())
    }

    private fun loadData(data: List<MovimentEntity>?) = mutableLiveData.postValue(data)

    fun onSortSelected(sortBalanceType: SortBalanceType) = loadData(sortData(sortBalanceType))

    /**
     * Método onde fazemos ordenação de acordo com o tipo de ordenação
     */
    private fun sortData(sortType: SortBalanceType): List<MovimentEntity>? {
        return when (sortType) {
            SortBalanceType.DATE_ASC -> data.value?.sortedBy { it.date }
            SortBalanceType.DATE_DESC -> data.value?.sortedBy { -it.date }
            SortBalanceType.VALUE_ASC -> data.value?.sortedBy { it.value }
            SortBalanceType.VALUE_DESC -> data.value?.sortedBy { -it.value }
        }
    }

}