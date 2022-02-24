package com.nvqquy98.moneyqq.presentation.ui.resetpassword

import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentResetPasswordBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding, ResetPasswordViewModel>() {
    override val layoutId: Int = R.layout.fragment_reset_password
    override val viewModel: ResetPasswordViewModel by viewModel()

}