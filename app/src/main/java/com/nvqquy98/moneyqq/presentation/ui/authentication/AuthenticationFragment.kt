package com.nvqquy98.moneyqq.presentation.ui.authentication

import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentAuthenticationBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthenticationFragment : BaseFragment<FragmentAuthenticationBinding, EmptyViewModel>() {
    override val layoutId: Int = R.layout.fragment_authentication
    override val viewModel: EmptyViewModel by viewModel()

}