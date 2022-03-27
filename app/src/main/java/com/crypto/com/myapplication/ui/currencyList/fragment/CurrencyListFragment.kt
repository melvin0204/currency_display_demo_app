package com.crypto.com.myapplication.ui.currencyList.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.crypto.com.myapplication.R
import com.crypto.com.myapplication.base.BaseFragment
import com.crypto.com.myapplication.data.model.Currency
import com.crypto.com.myapplication.databinding.FragmentCurrencyListBinding
import com.crypto.com.myapplication.di.injectViewModel
import com.crypto.com.myapplication.ui.currencyList.viewModel.DemoViewModel
import com.google.android.material.snackbar.Snackbar
import com.crypto.com.myapplication.data.model.Result
import com.crypto.com.myapplication.ui.currencyList.fragment.adapter.CurrencyListAdapter

class CurrencyListFragment : BaseFragment<FragmentCurrencyListBinding, DemoViewModel>() {

    private lateinit var adapter: CurrencyListAdapter

    override fun getViewModelClass(): Class<DemoViewModel> = DemoViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_currency_list

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelFactory, fromActivity = true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CurrencyListAdapter(this::onItemClicked)

        binding.rvCurrencyList.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        binding.adapter = adapter
        observeCurrency()
    }

    private fun observeCurrency() {
        viewModel.currencyList.observe(viewLifecycleOwner, { result ->
            when (result.status) {

                Result.Status.SUCCESS -> {
                    if (result.data != null) {
                        adapter.setCurrencyList(result.data)
                    }
                    binding.rvCurrencyList.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        Snackbar.make(
                            binding.rvCurrencyList,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    binding.rvCurrencyList.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun onItemClicked(currency: Currency) {
        Toast.makeText(context, "${currency.name} clicked", Toast.LENGTH_SHORT).show()
    }

}