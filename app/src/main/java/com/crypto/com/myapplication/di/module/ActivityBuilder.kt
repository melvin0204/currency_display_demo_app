package com.crypto.com.myapplication.di.module

import com.crypto.com.myapplication.ui.currencyList.activity.DemoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): DemoActivity

}