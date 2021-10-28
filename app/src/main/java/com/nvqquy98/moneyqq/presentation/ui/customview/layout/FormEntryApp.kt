package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class FormEntryApp @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setBackgroundColor(Color.TRANSPARENT)
        paint.setShadowLayer(20F, 0F, 4F, Color.parseColor("#DDDDDD"))
        paint.color = Color.WHITE
        paint.strokeJoin = Paint.Join.ROUND
        paint.isAntiAlias = true
        paint.strokeWidth = 5F
        paint.style = Paint.Style.FILL
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
        paint.pathEffect = CornerPathEffect(40F)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            val height = height.toFloat()
            val width = width.toFloat()
           val point = mutableListOf(40F to 40F, 40F to height-40F,width-40F to height-40F, width-40F to height * 0.35F-40F )

            path.run {
                moveTo(120F,0F)
                point.forEach {
                    lineTo(it.first,it.second)
                }

                path.close();
            }
            canvas.drawPath(path, paint)
        }
        super.onDraw(canvas)
    }

}