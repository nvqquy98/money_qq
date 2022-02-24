package com.nvqquy98.moneyqq.presentation.ui.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nvqquy98.moneyqq.BR

open class BaseViewHolder<T : Any, out B : ViewDataBinding> constructor(val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context by lazy {
        binding.root.context
    }

    open fun bind(item: T) {
//        binding.setVariable(BR.item, item)
    }
}
