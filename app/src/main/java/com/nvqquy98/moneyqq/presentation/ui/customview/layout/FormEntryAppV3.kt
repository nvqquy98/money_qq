package com.nvqquy98.moneyqq.presentation.ui.customview.layout

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.nvqquy98.moneyqq.R
import kotlin.math.roundToInt

class FormEntryAppV3 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Attr Radius
    private var topStartRadius = 0F
    private var topEndRadius = 0F
    private var bottomStartRadius = 0F
    private var bottomEndRadius = 0F
    private var topRadius = 0F
    private var bottomRadius = 0F
    private var startRadius = 0F
    private var endRadius = 0F
    private var radius = 0F
    private var blurShadow = 0F
    private var xShadow = 0F
    private var yShadow = 0F
    private var shadowColor = Color.TRANSPARENT
    private var backgroundFill = Color.TRANSPARENT
    private var arrowSizeTop = 0F
    private var arrowSizeBottom = 0F
    private var isShadowHorizontal = true

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ConstraintLayoutCustom)
        try {
            topStartRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_topStartRadius,
                    DEFAULT_VALUE
                )
            topEndRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_topEndRadius,
                    DEFAULT_VALUE
                )
            bottomStartRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_bottomStartRadius,
                    DEFAULT_VALUE
                )
            bottomEndRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_bottomEndRadius,
                    DEFAULT_VALUE
                )
            topRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_topRadius,
                    DEFAULT_VALUE
                )
            bottomRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_bottomRadius,
                    DEFAULT_VALUE
                )
            startRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_startRadius,
                    DEFAULT_VALUE
                )
            endRadius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_endRadius,
                    DEFAULT_VALUE
                )
            radius =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_radius,
                    DEFAULT_VALUE
                )
            blurShadow =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_blurShadow,
                    DEFAULT_VALUE
                )
            xShadow =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_xShadow,
                    DEFAULT_VALUE
                )
            yShadow =
                typeArray.getDimension(
                    R.styleable.ConstraintLayoutCustom_yShadow,
                    DEFAULT_VALUE
                )
            shadowColor = typeArray.getColor(
                R.styleable.ConstraintLayoutCustom_shadowColor,
                DEFAULT_COLOR
            )
            backgroundFill = typeArray.getColor(
                R.styleable.ConstraintLayoutCustom_backgroundFill,
                DEFAULT_COLOR
            )
            val arrowSize = typeArray.getDimension(
                R.styleable.ConstraintLayoutCustom_arrowSize,
                DEFAULT_VALUE
            )
            when (typeArray.getInt(R.styleable.ConstraintLayoutCustom_arrow, ARROW_NONE)) {
                ARROW_NONE -> {
                    arrowSizeTop = DEFAULT_VALUE
                    arrowSizeBottom = DEFAULT_VALUE
                }
                ARROW_TOP -> {
                    arrowSizeTop = arrowSize
                }
                ARROW_BOTTOM -> {
                    arrowSizeBottom = arrowSize
                }

                ARROW_BOTH -> {
                    arrowSizeTop = arrowSize
                    arrowSizeBottom = arrowSize
                }
            }
            isShadowHorizontal =
                typeArray.getBoolean(
                    R.styleable.ConstraintLayoutCustom_isShadowHorizontal,
                    true
                )
        } finally {
            typeArray.recycle()
        }
        setupAttrRadius()
        setupView()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            val (startPointX, width) = if (isShadowHorizontal) blurShadow to (width.toFloat() - blurShadow) else DEFAULT_VALUE to width.toFloat()
            initPoint(
                startPointX = startPointX,
                width = width,
                height = height.toFloat()
            ).forEach {
                lineToWithRadius(it)
            }
            canvas.drawPath(path, paint)
        }
        super.onDraw(canvas)
    }

    private fun setupAttrRadius() {
        if (radius != 0F) {
            topRadius = radius
            bottomRadius = radius
            startRadius = radius
            endRadius = radius
        }
        if (topRadius != 0F) {
            topStartRadius = topRadius
        }
        if (topRadius != 0F) {
            topStartRadius = topRadius
            topEndRadius = topRadius
        }
        if (bottomRadius != 0F) {
            bottomStartRadius = bottomRadius
            bottomEndRadius = bottomRadius
        }
        if (startRadius != 0F) {
            topStartRadius = startRadius
            bottomStartRadius = startRadius
        }
        if (endRadius != 0F) {
            topEndRadius = endRadius
            bottomEndRadius = endRadius
        }
    }

    private fun setupView() {
        setBackgroundColor(Color.TRANSPARENT)
        layoutParams = LayoutParams(width, height + (blurShadow * 2).roundToInt()).apply {
            setPadding(
                if (isShadowHorizontal) paddingStart + blurShadow.roundToInt() else paddingStart,
                paddingTop + (blurShadow + arrowSizeTop).roundToInt(),
                if (isShadowHorizontal) paddingEnd + blurShadow.roundToInt() else paddingEnd,
                paddingBottom + (blurShadow + arrowSizeBottom).roundToInt()
            )
        }
        paint.run {
            setShadowLayer(blurShadow, xShadow, yShadow, shadowColor)
            isAntiAlias = true
            style = Paint.Style.FILL
            color = backgroundFill
        }
        setLayerType(LAYER_TYPE_SOFTWARE, paint)
    }

    private fun initPoint(startPointX: Float, width: Float, height: Float): List<PointDraw> {
        return listOf(
            PointDraw(
                x = startPointX,
                y = blurShadow,
                radius = topStartRadius,
                gravity = PointDraw.Gravity.TOP_START
            ),
            PointDraw(
                x = width / 2F,
                y = arrowSizeTop + blurShadow,
                radius = 0F,
                gravity = PointDraw.Gravity.CENTER
            ),
            PointDraw(
                x = width,
                y = blurShadow,
                radius = topEndRadius,
                gravity = PointDraw.Gravity.TOP_END
            ),
            PointDraw(
                x = width,
                y = height - arrowSizeBottom - blurShadow,
                radius = bottomEndRadius,
                gravity = PointDraw.Gravity.BOTTOM_END
            ),
            PointDraw(
                x = width / 2F,
                y = height - blurShadow,
                radius = 0F,
                gravity = PointDraw.Gravity.CENTER
            ),
            PointDraw(
                x = startPointX,
                y = height - arrowSizeBottom - blurShadow,
                radius = bottomStartRadius,
                gravity = PointDraw.Gravity.BOTTOM_START
            )
        )
    }

    private fun lineToWithRadius(pointDraw: PointDraw) {
        if (pointDraw.radius == 0F) {
            if (pointDraw.gravity == PointDraw.Gravity.TOP_START) {
                path.moveTo(pointDraw.x, pointDraw.y)
            }
            path.lineTo(pointDraw.x, pointDraw.y)
            return
        }
        path.run {
            when (pointDraw.gravity) {
                PointDraw.Gravity.TOP_START -> {
                    moveTo(pointDraw.x, pointDraw.y + pointDraw.radius)
                    quadTo(pointDraw.x, pointDraw.y, pointDraw.x + pointDraw.radius, pointDraw.y)
                }
                PointDraw.Gravity.TOP_END -> {
                    lineTo(pointDraw.x - pointDraw.radius, pointDraw.y)
                    quadTo(pointDraw.x, pointDraw.y, pointDraw.x, pointDraw.y + pointDraw.radius)
                }
                PointDraw.Gravity.BOTTOM_END -> {
                    lineTo(pointDraw.x, pointDraw.y - pointDraw.radius)
                    quadTo(pointDraw.x, pointDraw.y, pointDraw.x - pointDraw.radius, pointDraw.y)
                }
                PointDraw.Gravity.BOTTOM_START -> {
                    lineTo(pointDraw.x + pointDraw.radius, pointDraw.y)
                    quadTo(pointDraw.x, pointDraw.y, pointDraw.x, pointDraw.y - pointDraw.radius)
                }
                else -> {
                }
            }
        }
    }

    data class PointDraw(
        val x: Float,
        val y: Float,
        val radius: Float = 0F,
        val gravity: Gravity? = null
    ) {
        enum class Gravity {
            CENTER,
            TOP_START,
            TOP_END,
            BOTTOM_START,
            BOTTOM_END,
        }
    }

    companion object {
        const val ARROW_NONE = 0
        const val ARROW_TOP = 1
        const val ARROW_BOTTOM = 2
        const val ARROW_BOTH = 3
        const val DEFAULT_VALUE = 0F
        const val DEFAULT_COLOR = Color.TRANSPARENT
    }
}

/**
 * radius : top left ,top right, bottom left,bottom right, top,bottom , all(radius),start ,end
 * background_color, shadow color , blur ,x, y
 * arrow : top | bottom | none | both
 * arrowSize:
 **/