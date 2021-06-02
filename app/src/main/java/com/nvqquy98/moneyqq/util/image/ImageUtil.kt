package com.nvqquy98.moneyqq.util.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.util.Constants
import java.io.File

fun Bitmap.resize(maxSize: Int): Bitmap {
    var w = width
    var h = height
    val ratio: Float = w.toFloat() / h.toFloat()
    if (ratio > 1) {
        w = maxSize
        h = (w / ratio).toInt()
    } else {
        h = maxSize
        w = (h * ratio).toInt()
    }
    return Bitmap.createScaledBitmap(this, w, h, true)
}

fun File.hasValidSize() = this.length() <= Constants.IMAGE_MAX_SIZE_BYTES

private val roundedTransform: (corners: Int) -> BitmapTransformation = {
    RoundedCorners(it)
}

private val topRoundedTransform: (corners: Float) -> BitmapTransformation = {
    GranularRoundedCorners(it, it, 0f, 0f)
}

private val defaultCorners: (context: Context) -> Int = {
    it.resources.getDimensionPixelSize(R.dimen.dp_6)
}

private val circleTransform = CircleCrop()

fun ImageView.loadRectangleImage(url: String?, width: Int? = null, height: Int? = null) {
    GlideApp.with(context).load(url).apply {
        if (width != null && height != null) override(width, height)
    }.into(this)
}

fun ImageView.loadRectangleImage(file: File?, width: Int? = null, height: Int? = null) {
    GlideApp.with(context).load(file).apply {
        if (width != null && height != null) override(width, height)
    }.into(this)
}

fun ImageView.loadRectangleImageFilePath(path: String?, width: Int? = null, height: Int? = null) {
    loadRectangleImage(path?.let { File(it) }, width, height)
}

fun ImageView.loadCircleImage(url: String?, diameter: Int? = null) {
    GlideApp.with(context).load(url).transform(circleTransform).apply {
        if (diameter != null) override(diameter)
    }.placeholder(R.drawable.ic_circle_place_holder).into(this)
}

fun ImageView.loadCircleImage(file: File?, diameter: Int? = null) {
    GlideApp.with(context).load(file).transform(circleTransform).apply {
        if (diameter != null) override(diameter)
    }.placeholder(R.drawable.ic_circle_place_holder).into(this)
}

fun ImageView.loadCircleImageFilePath(path: String?, diameter: Int? = null) {
    loadCircleImage(path?.let { File(it) }, diameter)
}

fun ImageView.loadRoundedImage(
    url: String?,
    radius: Int? = defaultCorners(context),
    width: Int? = null,
    height: Int? = null
) {
    GlideApp.with(context).load(url).transform(
        CenterCrop(),
        roundedTransform(radius ?: defaultCorners(context))
    ).apply {
        if (width != null && height != null) override(width, height)
    }.into(this)
}

fun ImageView.loadTopRoundedImage(
    url: String?,
    radius: Float? = defaultCorners(context).toFloat(),
    width: Int? = null,
    height: Int? = null
) {
    GlideApp.with(context).load(url).transform(
        CenterCrop(),
        topRoundedTransform(radius ?: defaultCorners(context).toFloat())
    ).apply {
        if (width != null && height != null) override(width, height)
    }.into(this)
}

fun ImageView.loadRectangleImage(bitmap: Bitmap?, width: Int? = null, height: Int? = null) {
    GlideApp.with(context).load(bitmap).apply {
        if (width != null && height != null) override(width, height)
    }.into(this)
}

fun ImageView.loadCircleImage(bitmap: Bitmap?, diameter: Int? = null) {
    GlideApp.with(context).load(bitmap).apply {
        if (diameter != null) override(diameter)
    }.transform(circleTransform).placeholder(R.drawable.ic_circle_place_holder).into(this)
}

fun ImageView.loadRectangleImage(@RawRes @DrawableRes drawableId: Int) {
    GlideApp.with(context).load(drawableId).into(this)
}

fun ImageView.loadRectangleImageAsBitmap(file: File?, width: Int? = null, height: Int? = null) {
    GlideApp.with(context)
        .asBitmap()
        .apply {
            if (width != null && height != null) override(width, height)
        }
        .load(file)
        .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}

fun ImageView.loadRectangleImageAsBitmap(uri: String?, width: Int? = null, height: Int? = null) {
    GlideApp.with(context)
        .asBitmap()
        .apply {
            if (width != null && height != null) override(width, height)
        }
        .load(uri)
        .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}
