package com.nvqquy98.moneyqq.presentation.binding

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.nvqquy98.moneyqq.util.image.loadCircleImage
import com.nvqquy98.moneyqq.util.image.loadRectangleImage
import com.nvqquy98.moneyqq.util.image.loadRoundedImage
import com.nvqquy98.moneyqq.util.image.loadTopRoundedImage
import java.io.File
import kotlin.math.roundToInt

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isVisible")
    fun isVisible(view: View, isShowing: Boolean?) {
        view.isVisible = isShowing == true
    }

    @JvmStatic
    @BindingAdapter("isVisibleWithAnimation")
    fun isVisibleWithAnimation(view: View, isShowing: Boolean?) {
        view.clearAnimation()
        if (isShowing == true) {
            view.isVisible = true
            view.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        view.isVisible = true
                    }
                })
                .start()
        } else {
            view.animate()
                .alpha(0f)
                .setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        view.isVisible = false
                    }
                })
                .start()
        }
    }

    @JvmStatic
    @BindingAdapter("isGone")
    fun isGone(view: View, isGone: Boolean?) {
        view.isGone = isGone == true
    }

    @JvmStatic
    @BindingAdapter("isInvisible")
    fun isInvisible(view: View, isInvisible: Boolean?) {
        view.isInvisible = isInvisible == true
    }

    @JvmStatic
    @BindingAdapter(value = ["rectangleImageUrl", "imageWidth", "imageHeight"], requireAll = false)
    fun rectangleImageUrl(imageView: ImageView, url: String?, width: Float?, height: Float?) {
        imageView.loadRectangleImage(url, width?.roundToInt(), height?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(value = ["circleImageUrl", "diameter"], requireAll = false)
    fun circleImageUrl(imageView: ImageView, url: String?, diameter: Float?) {
        imageView.loadCircleImage(url, diameter?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(value = ["rectangleBitmap", "imageWidth", "imageHeight"], requireAll = false)
    fun rectangleBitmap(imageView: ImageView, bitmap: Bitmap?, width: Float?, height: Float?) {
        imageView.loadRectangleImage(bitmap, width?.roundToInt(), height?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(value = ["circleBitmap", "diameter"], requireAll = false)
    fun circleBitmap(imageView: ImageView, bitmap: Bitmap?, diameter: Float?) {
        imageView.loadCircleImage(bitmap, diameter?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(value = ["circleFile", "diameter"], requireAll = false)
    fun circleFilePath(imageView: ImageView, file: File?, diameter: Float?) {
        imageView.loadCircleImage(file, diameter?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(
        value = ["roundedImage", "radius", "imageWidth", "imageHeight"],
        requireAll = false
    )
    fun roundedImage(
        imageView: ImageView,
        url: String?,
        radius: Float?,
        width: Float?,
        height: Float?
    ) {
        imageView.loadRoundedImage(url, radius?.toInt(), width?.roundToInt(), height?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter(
        value = ["topRoundedImage", "topRadius", "imageWidth", "imageHeight"],
        requireAll = false
    )
    fun topRoundedImage(
        imageView: ImageView,
        url: String?,
        radius: Float?,
        width: Float?,
        height: Float?
    ) {
        imageView.loadTopRoundedImage(url, radius, width?.roundToInt(), height?.roundToInt())
    }

    @JvmStatic
    @BindingAdapter("layoutWidth")
    fun setLayoutWidth(view: View, width: Float) {
        val layoutParams = view.layoutParams.apply { this.width = width.toInt() }
        view.layoutParams = layoutParams
    }

    @JvmStatic
    @BindingAdapter("isBoldText")
    fun isBoldText(textView: TextView, isBold: Boolean?) {
        val typeface = if (isBold == true) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        textView.setTypeface(typeface)
    }

    @JvmStatic
    @BindingAdapter(value = ["marginBottom", "marginEnd"], requireAll = false)
    fun setMargin(view: View, marginBottom: Float?, marginEnd: Float?) {
        val layoutParams = view.layoutParams as? ViewGroup.MarginLayoutParams
        view.layoutParams = layoutParams?.apply {
            bottomMargin = marginBottom?.toInt() ?: bottomMargin
            rightMargin = marginEnd?.toInt() ?: rightMargin
        }
    }

    @JvmStatic
    @BindingAdapter("layoutHeight")
    fun setLayoutHeight(view: View, height: Float) {
        val layoutParams = view.layoutParams.apply { this.height = height.toInt() }
        view.layoutParams = layoutParams
    }
}