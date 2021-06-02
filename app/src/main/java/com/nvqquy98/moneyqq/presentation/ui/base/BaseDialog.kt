package com.nvqquy98.moneyqq.presentation.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

abstract class BaseDialog<B : ViewDataBinding>(context: Context) : Dialog(context) {
    @get:LayoutRes
    abstract val layoutId: Int

    lateinit var binding: B

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    dismiss()
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        setContentView(binding.root)
    }
}
