package com.nvqquy98.moneyqq.util.view

import android.animation.AnimatorInflater
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun View.safeClick(blockInMillis: Long = 1000, onClick: (View) -> Unit) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        onClick(this)
    }
}

fun Fragment.showToast(messenger: String) {
    Toast.makeText(requireContext(), messenger, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToast(messenger: String) {
    Toast.makeText(this, messenger, Toast.LENGTH_SHORT).show()
}

fun View.loadAnimation(idAnimation: Int) {
    val animation = AnimatorInflater.loadAnimator(context, idAnimation)
    animation.setTarget(this)
    animation.start()
}