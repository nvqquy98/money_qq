package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class FormEntryAppV2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setBackgroundColor(Color.TRANSPARENT)
        paint.setShadowLayer(30F, 0F, 0F, Color.parseColor("#0D000000"))
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
//        paint.pathEffect = CornerPathEffect(40F)
    }

    /**
     *
    Dư 4 cạnh 1 cạnh 10F để show shadow
    (10F,10F)             (width -10F , 10F)
    (Width/2, 80F)


    (10F,height - 90F)      (width-10F, height - 90F)
    (Width/2,height - 10F)

     **/
    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            val height = height.toFloat()
            val width = width.toFloat()
            val startX = 0F
            val startY = 30F
            val peak = 80F
            val point = mutableListOf(
                startX to startY,
                (width / 2F) to peak + startY,
                width to startY,
                width to height - peak - startY,
                (width / 2F) to height - startY,
                startX to height - peak - startY,
                startX to startY
            )

            path.run {
                moveTo(startX, startY)
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