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

    private val mutableSortTypeLiveData = MutableLiveData<SortBalanceType>()
    val sortTypeData: LiveData<SortBalanceType> = mutableSortTypeLiveData

    init {
        loadData()
    }

    private fun loadData() {
        mutableLiveData.postValue(storage.getMoviments())
    }

    fun onSortSelected(sortBalanceType: SortBalanceType) {
        mutableSortTypeLiveData.value = sortBalanceType
    }

}