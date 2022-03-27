package com.crypto.com.myapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crypto.com.myapplication.di.ViewModelKey
import com.crypto.com.myapplication.di.factory.ViewModelFactory
import com.crypto.com.myapplication.ui.currencyList.viewModel.DemoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DemoViewModel::class)
    internal abstract fun providesDemoViewModel(viewModel: DemoViewModel): ViewModel

}