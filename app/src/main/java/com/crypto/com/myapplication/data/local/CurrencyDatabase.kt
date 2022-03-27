package com.crypto.com.myapplication.data.local

import com.crypto.com.myapplication.data.local.dao.CurrencyDao
import java.util.*
import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.crypto.com.myapplication.R
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider
import com.crypto.com.myapplication.data.model.Currency;
import com.crypto.com.myapplication.di.module.ApplicationScope
import com.google.gson.Gson


@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    class CurrencyCallback @Inject constructor(
        private val database: Provider<CurrencyDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
        private val resources: Resources
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.d("ONCREATE","Database has been created.");

            val currencyDao = database.get().currencyDao()

            applicationScope.launch{
                prePopulateDatabase(currencyDao)
            }
        }

        private suspend fun prePopulateDatabase(currencyDao: CurrencyDao){

            val jsonString = resources.openRawResource(R.raw.currency_list).bufferedReader().use {
                it.readText()
            }

            val typeToken = object : TypeToken<List<Currency>>() {}.type
            val currencies = Gson().fromJson<List<Currency>>(jsonString, typeToken)

            currencyDao.saveAll(currencies)
        }
    }

    companion object {

        @Volatile
        private var instance: CurrencyDatabase? = null

        fun getInstance(context: Context, callback: CurrencyCallback): CurrencyDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, callback).also { instance = it }
            }

        }

        private fun buildDatabase(context: Context, callback: CurrencyCallback): CurrencyDatabase {
            val db = Room.databaseBuilder(context, CurrencyDatabase::class.java, "currencydb")
                .addCallback(callback)
                .build()
            return db
        }
    }
}