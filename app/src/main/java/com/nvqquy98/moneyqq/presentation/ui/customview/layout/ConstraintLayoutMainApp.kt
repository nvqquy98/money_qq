package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.util.view.loadAnimation

class ConstraintLayoutMainApp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    init {
        background = ContextCompat.getDrawable(context,R.drawable.background_main_app)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun hiddenNavigationAnimation() {
        background = ContextCompat.getDrawable(context, R.drawable.background_main_app)
        loadAnimation(R.animator.tranform_hiden_navigation)
    }

    fun showNavigationAnimation() {
        background = ContextCompat.getDrawable(context, R.drawable.background_main_app_corner)
        loadAnimation(R.animator.tranform_show_navigation)

    }
}