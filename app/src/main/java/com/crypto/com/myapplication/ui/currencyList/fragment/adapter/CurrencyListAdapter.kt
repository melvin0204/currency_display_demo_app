package com.crypto.com.myapplication.ui.currencyList.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.crypto.com.myapplication.R
import com.crypto.com.myapplication.data.model.Currency
import com.crypto.com.myapplication.databinding.ItemCurrencyBinding


class CurrencyListAdapter(val listener: Listener) : RecyclerView.Adapter<CurrencyListAdapter.Holder>() {
    private val list = arrayListOf<Currency>()

    internal fun setCurrencyList(list: List<Currency>) {
        if (this.list.isNotEmpty()) {
            this.list.clear()
        }
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemCurrencyBinding>(inflater, R.layout.item_currency, parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener(list[position])
        }
    }

    inner class Holder(var binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Currency) = binding.apply {
            currency = item.let { Currency(it.id.first().toString(), it.name, it.symbol) }
            executePendingBindings()
        }
    }
}

typealias Listener = (Currency) -> Unit
