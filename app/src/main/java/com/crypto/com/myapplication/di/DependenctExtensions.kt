package com.crypto.com.myapplication.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider





inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory, fromActivity: Boolean): T {
    if (fromActivity)
        return ViewModelProvider(activity!!)[T::class.java]
    return ViewModelProvider(this, factory)[T::class.java]
}
