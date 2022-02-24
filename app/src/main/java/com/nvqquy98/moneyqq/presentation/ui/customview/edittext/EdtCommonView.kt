package com.nvqquy98.moneyqq.presentation.ui.customview.edittext

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.LayoutEdtCommonBinding
import com.nvqquy98.moneyqq.util.Constants.EMPTY
import com.nvqquy98.moneyqq.util.Constants.NUMBER_0

class EdtCommonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    val binding: LayoutEdtCommonBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.layout_edt_common,
        this,
        true
    )
    private val edt = binding.edt
    val text get() = edt.text.toString()
    private var textColor: Int = Color.BLACK
        set(value) {
            edt.setTextColor(value)
            field = value
        }
    private var hintText: CharSequence = EMPTY
        set(value) {
            edt.hint = value
            field = value
        }
    private var hintTextColor: Int = Color.BLACK
        set(value) {
            edt.setHintTextColor(value)
            field = value
        }
    private var icStart: Drawable? = null
        set(value) {
            binding.iconLeft.setImageDrawable(value)
            field = value
        }
    private var typeTextInput: Int = NUMBER_0
        set(value) {
            edt.inputType = when (value) {
                INPUT_TYPE_EMAIL -> {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                INPUT_TYPE_PASSWORD -> {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                INPUT_TYPE_MULTI_LINE -> {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                }
                else -> {
                    InputType.TYPE_CLASS_TEXT
                }
            }
            field = value
        }
    private var focusedEdtColor: Int = Color.BLACK
    private var unfocusedEdtColor: Int = Color.BLACK
        set(value) {
            setColorFocusEdt(value)
            field = value
        }
    var errorMessage: String = EMPTY
        set(value) {
            binding.tvError.text = value
            binding.tvError.isVisible = value.isNotBlank()
            field = value
        }

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EdtCommonView)
        try {
            textColor =
                typedArray.getColor(R.styleable.EdtCommonView_android_textColor, Color.BLACK)
            hintText = typedArray.getText(R.styleable.EdtCommonView_android_hint)
            hintTextColor =
                typedArray.getColor(R.styleable.EdtCommonView_android_textColorHint, Color.BLACK)
            icStart = typedArray.getDrawable(R.styleable.EdtCommonView_ecv_ic_start)
            typeTextInput = typedArray.getInt(R.styleable.EdtCommonView_ecv_type_text, NUMBER_0)
            focusedEdtColor =
                typedArray.getColor(R.styleable.EdtCommonView_ecv_focused_color, Color.BLACK)
            unfocusedEdtColor =
                typedArray.getColor(R.styleable.EdtCommonView_ecv_unfocused_color, Color.BLACK)
        } finally {
            typedArray.recycle()
        }
        edt.setOnFocusChangeListener { _, hasFocus ->
            setColorFocusEdt(if (hasFocus) focusedEdtColor else unfocusedEdtColor)
        }
    }

    private fun setColorFocusEdt(color: Int) {
        binding.iconLeft.setColorFilter(color)
        binding.divider.setBackgroundColor(color)
    }

    companion object {
        const val INPUT_TYPE_EMAIL = 1
        const val INPUT_TYPE_PASSWORD = 2
        const val INPUT_TYPE_MULTI_LINE = 3
    }
}