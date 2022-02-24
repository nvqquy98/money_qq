package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import com.nvqquy98.moneyqq.R

class LeftNavigationApp @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    data class ItemMenu(val name: String, val drawableId: Int, val isCheck: Boolean)

    interface OnItemClickListeners {
        fun onItemClick(@IdRes id: Int)
    }

    private var onItemClickListeners: OnItemClickListeners? = null

    fun initView(items: List<ItemMenu>) {
        items.forEach {

        }
    }

    fun setOnItemClick(onItemClickListeners: OnItemClickListeners) {
        this.onItemClickListeners = onItemClickListeners
    }

    private fun initView(item: ItemMenu) {
        val view = AppCompatImageView(context)
        view.setImageResource(item.drawableId)
        view.setColorFilter(context.getColor(if (item.isCheck) R.color.purple_700 else R.color.black))
        addView(view)
    }
}