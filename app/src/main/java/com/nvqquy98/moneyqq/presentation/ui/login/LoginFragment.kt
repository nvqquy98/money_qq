package com.nvqquy98.moneyqq.presentation.ui.login

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentLoginAccountBinding
import com.nvqquy98.moneyqq.presentation.ui.authentication.AuthenticationFragmentDirections
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.util.view.safeClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginAccountBinding, LoginViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_login_account
    override val viewModel: LoginViewModel by viewModel()

    override fun handleEvents() {
        with(binding) {
            tvRegister.safeClick {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            tvForgotPassword.safeClick {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSendEmailFragment())
            }
            btnLogin.safeClick {
                viewModel.login(email = edtEmail.text, password = edtPassword.text)
            }
        }
    }

    override fun observe() {
        with(viewModel) {
            loginSuccess.observe(viewLifecycleOwner, Observer {
                navRootController.navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToHomeFragment())
            })
            loginError.observe(viewLifecycleOwner, Observer {
                binding.edtPassword.errorMessage = getString(R.string.email_or_password_incorrect)
            })
        }
    }
}
