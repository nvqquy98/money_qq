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
        paint.style = Paint.Style.FILL
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
        paint.pathEffect = CornerPathEffect(20F)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            val height = height.toFloat()
            val width = width.toFloat()
            val point = mutableListOf(
                20F to 20F,
                20F to height - 20F,
                width - 20F to height - 20F,
                width - 20F to height * 0.35F - 20F
            )

            path.run {
                moveTo(60F, 0F)
                point.forEach {
                    lineTo(it.first, it.second)
                }

                path.close();
            }
            canvas.drawPath(path, paint)
        }
        super.onDraw(canvas)
    }
}