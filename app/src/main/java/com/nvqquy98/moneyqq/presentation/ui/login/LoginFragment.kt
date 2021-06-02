package com.nvqquy98.moneyqq.presentation.ui.login

import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentLoginBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding,EmptyViewModel>(){
    override val layoutId: Int
        get() = R.layout.fragment_login
    override val viewModel: EmptyViewModel by viewModel()


}