package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class FormEntryApp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.setShadowLayer(20F,0F,4F , Color.parseColor("#DDDDDD"))
        paint.color = Color.WHITE
        paint.strokeJoin = Paint.Join.ROUND
        paint.isAntiAlias = true
        paint.strokeWidth = 20F
        paint.style = Paint.Style.FILL_AND_STROKE
        setLayerType(LAYER_TYPE_SOFTWARE,paint)
    }
    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            val height = height.toFloat()
            val width = width.toFloat()

            path.run {
                lineTo(0F,height-50F)
                arcTo(0F,height-100F,100F,height,90F,90F,true)
                moveTo(50F,height)
                lineTo(width-50F,height)
                arcTo(width-100F,height-100,width,height,0F,90F,true)
                moveTo(width,height-50F)
                lineTo(width,height*0.25F)
                lineTo(0F,0F)
            }
            canvas.drawPath(path,paint)
        }
        super.onDraw(canvas)
    }
}