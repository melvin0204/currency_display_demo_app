package com.crypto.com.myapplication.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.crypto.com.myapplication.CurrencyListApp
import com.crypto.com.myapplication.data.CurrencyRepository
import com.crypto.com.myapplication.data.local.CurrencyDatabase
import com.crypto.com.myapplication.data.local.dao.CurrencyDao
import com.crypto.com.myapplication.ui.currencyList.fragment.CurrencyListFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: CurrencyListApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: CurrencyListApp): Application = app

    @Provides
    @Singleton
    fun provideRoomDB(app: Application, callback: CurrencyDatabase.CurrencyCallback) = CurrencyDatabase.getInstance(app, callback)

    @Provides
    @Singleton
    fun provideCurrencyDao(db: CurrencyDatabase) = db.currencyDao()

    @Provides
    @Singleton
    @Named("IO")
    fun provideBackgroundDispatchers(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    @Named("MAIN")
    fun provideMainDispatchers(): CoroutineDispatcher =
        Dispatchers.Main


    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources


    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideCurrencyListFragment(): CurrencyListFragment = CurrencyListFragment()

    @Provides
    @Singleton
    fun provideCurrencyRepository(dao: CurrencyDao) =
        CurrencyRepository(dao)

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope