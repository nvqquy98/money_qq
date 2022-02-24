package com.nvqquy98.moneyqq.mainapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.ActivityMainBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        super.onCreate(savedInstanceState)
    }
}
