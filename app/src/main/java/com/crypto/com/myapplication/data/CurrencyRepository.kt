package com.crypto.com.myapplication.data

import com.crypto.com.myapplication.data.local.dao.CurrencyDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(
    private val dao: CurrencyDao,
) {

    suspend fun getAllCurrency() = dao.loadCurrencies()

    suspend fun getAllCurrencySorted() = dao.loadSortedCurrencies()

}