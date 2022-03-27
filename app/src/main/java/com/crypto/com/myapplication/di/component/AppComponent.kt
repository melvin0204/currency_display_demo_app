package com.crypto.com.myapplication.di.component

import com.crypto.com.myapplication.CurrencyListApp
import com.crypto.com.myapplication.di.module.ActivityBuilder
import com.crypto.com.myapplication.di.module.AppModule
import com.crypto.com.myapplication.di.module.FragmentModule
import com.crypto.com.myapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class
    ]
)

interface AppComponent : AndroidInjector<CurrencyListApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CurrencyListApp): Builder

        fun build(): AppComponent
    }

}