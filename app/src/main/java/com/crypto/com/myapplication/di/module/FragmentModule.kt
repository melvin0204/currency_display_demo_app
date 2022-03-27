package com.crypto.com.myapplication.di.module

import com.crypto.com.myapplication.ui.currencyList.fragment.CurrencyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesCurrencyListFragment(): CurrencyListFragment

}