package com.nvqquy98.moneyqq.presentation.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentSplashBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, EmptyViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_splash

    override val viewModel: EmptyViewModel by viewModel()

    override fun initDataAndViews() {
        super.initDataAndViews()
        lifecycleScope.launchWhenResumed {
            delay(500)
            if (mainViewModel.userInfo == null) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthenticationFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}
