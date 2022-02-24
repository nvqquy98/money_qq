package com.nvqquy98.moneyqq.presentation.ui.main

import androidx.lifecycle.Observer
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentHomeBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by sharedViewModel()

    override fun observe() {
        with(viewModel) {
            logoutSuccess.observe(viewLifecycleOwner, Observer {
                navRootController.navigate(HomeFragmentDirections.actionHomeFragmentToAuthenticationFragment())
            })
        }
    }
}