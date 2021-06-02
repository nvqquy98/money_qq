package com.nvqquy98.moneyqq.presentation.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentSplashBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import com.nvqquy98.moneyqq.util.view.safeClick
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, EmptyViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_splash

    override val viewModel: EmptyViewModel by viewModel()
    private var isShow = false
    override fun initDataAndViews() {
        super.initDataAndViews()
        lifecycleScope.launchWhenResumed {
            delay(100)
            findNavController().navigate(R.id.action_SplashFragment_to_LoginFragment)
        }
        with(binding) {
            clickMe.safeClick {
                if (isShow) {
                    viewRotation.hiddenNavigationAnimation()
                } else {
                    viewRotation.showNavigationAnimation()
                }
                isShow = !isShow
            }
        }
    }
}