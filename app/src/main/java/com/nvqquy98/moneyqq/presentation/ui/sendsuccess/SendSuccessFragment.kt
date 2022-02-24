package com.nvqquy98.moneyqq.presentation.ui.sendsuccess

import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentSendSuccessBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.base.EmptyViewModel
import com.nvqquy98.moneyqq.util.view.safeClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendSuccessFragment : BaseFragment<FragmentSendSuccessBinding, EmptyViewModel>() {
    override val layoutId: Int = R.layout.fragment_send_success
    override val viewModel: EmptyViewModel by viewModel()

    override fun handleEvents() {
        with(binding) {
            btnLogin.safeClick {
                findNavController().navigate(SendSuccessFragmentDirections.actionSendSuccessFragmentToLoginFragment())
            }
        }
    }
}