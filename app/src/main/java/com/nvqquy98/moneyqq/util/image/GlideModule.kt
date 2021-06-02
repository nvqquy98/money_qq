package com.nvqquy98.moneyqq.util.image

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.nvqquy98.moneyqq.R

@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(requestOption())
    }

    private fun requestOption(): RequestOptions {
        return RequestOptions().placeholder(R.drawable.ic_place_holder)
    }
}