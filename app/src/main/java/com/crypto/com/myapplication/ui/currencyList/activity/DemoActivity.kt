package com.crypto.com.myapplication.ui.currencyList.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.crypto.com.myapplication.R
import com.crypto.com.myapplication.base.BaseActivity
import com.crypto.com.myapplication.data.model.Result
import com.crypto.com.myapplication.databinding.ActivityDemoBinding
import com.crypto.com.myapplication.di.injectViewModel
import com.crypto.com.myapplication.ui.currencyList.viewModel.DemoViewModel
import kotlinx.android.synthetic.main.activity_demo.*

class DemoActivity : BaseActivity<ActivityDemoBinding, DemoViewModel>() {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory)
    }

    override fun getViewModelClass(): Class<DemoViewModel> = DemoViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.activity_demo
    override fun initView() {
        val navFrag =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        setupActionBarWithNavController(navFrag.navController, AppBarConfiguration(navFrag.navController.graph))
        load_list_btn.setOnClickListener {
            mViewModel.displayCurrencyList() }
        sort_list_btn.setOnClickListener {
            mViewModel.sortCurrencyList() }

        observeCurrency()
    }



    private fun observeCurrency() {
        viewModel.currencyList.observe(this, { result ->
            when (result.status) {

                Result.Status.SUCCESS, Result.Status.ERROR -> {
                    load_list_btn.isEnabled = true
                    sort_list_btn.isEnabled = true
                }

                Result.Status.LOADING -> {
                    load_list_btn.isEnabled = false
                    sort_list_btn.isEnabled = false
                }
            }
        })
    }


}