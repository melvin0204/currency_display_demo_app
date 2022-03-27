package com.crypto.com.myapplication.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.crypto.com.myapplication.data.model.Currency

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(currencies: List<Currency>)

    @Query("SELECT * FROM currencies")
    fun loadCurrencies(): List<Currency>

    @Query("SELECT * FROM currencies ORDER BY name ASC")
    fun loadSortedCurrencies(): List<Currency>
}