package com.nvqquy98.moneyqq.mainapp

import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.ActivityMainBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutID: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
}
