package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class LinearLayoutCommon<T : Any, B : ViewDataBinding> @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : LinearLayout(context, attrs, defStyleAttr) {

    @get:LayoutRes
    abstract val layoutId: Int

    fun initView(item: List<T>) {
        item.forEach { addChildView(it) }
    }

    private fun addChildView(item: T): View {
        val binding: B =
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, false)
        bindItem(item, binding)
        return binding.root
    }

    abstract fun bindItem(item: T, binding: B)
}