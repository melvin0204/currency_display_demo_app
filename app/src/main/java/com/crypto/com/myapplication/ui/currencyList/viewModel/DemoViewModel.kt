package com.crypto.com.myapplication.ui.currencyList.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.com.myapplication.data.CurrencyRepository
import com.crypto.com.myapplication.data.model.Currency
import com.crypto.com.myapplication.data.model.Result
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

class DemoViewModel @Inject constructor(
    private val repository: CurrencyRepository,
    @Named("IO") private val io: CoroutineDispatcher,
    @Named("MAIN") private val main: CoroutineDispatcher
) : ViewModel() {

    private val _currencyList = MutableLiveData<Result<List<Currency>>>()
    val currencyList: LiveData<Result<List<Currency>>> get() = _currencyList

    private fun setResultCurrencyList(result: Result<List<Currency>>) {
        _currencyList.postValue(result)
    }

    fun displayCurrencyList() {
        viewModelScope.launch(main) {
            try {
                setResultCurrencyList(Result.loading())
                val result = async(context = io) {
                    repository.getAllCurrency()
                }
                setResultCurrencyList(Result.success(result.await()))
            } catch (e: Throwable) {
                setResultCurrencyList(Result.error(e.message ?: "Unknown error"))
            }
        }
    }

    fun sortCurrencyList() {
        viewModelScope.launch(main) {
            try {
                setResultCurrencyList(Result.loading())
                val result = async(context = io) {
                    repository.getAllCurrencySorted()
                }
                setResultCurrencyList(Result.success(result.await()))
            } catch (e: Throwable) {
                setResultCurrencyList(Result.error(e.message ?: "Unknown error"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
