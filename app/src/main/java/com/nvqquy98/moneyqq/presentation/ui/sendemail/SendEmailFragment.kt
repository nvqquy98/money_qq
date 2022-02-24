package com.nvqquy98.moneyqq.presentation.ui.sendemail

import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentSendEmailBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.util.view.safeClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendEmailFragment : BaseFragment<FragmentSendEmailBinding, SendEmailViewModel>() {
    override val layoutId: Int = R.layout.fragment_send_email
    override val viewModel: SendEmailViewModel by viewModel()

    override fun handleEvents() {
        with(binding) {
            btnSendEmail.safeClick {
                //TODO send email here
                findNavController().navigate(SendEmailFragmentDirections.actionSendEmailFragmentToSendSuccessFragment())
            }
            tvLogin.safeClick {
                findNavController().navigateUp()
            }
        }
    }
}
