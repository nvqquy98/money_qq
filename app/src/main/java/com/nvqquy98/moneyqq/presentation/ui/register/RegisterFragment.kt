package com.nvqquy98.moneyqq.presentation.ui.register

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentRegisterAccountBinding
import com.nvqquy98.moneyqq.presentation.ui.authentication.AuthenticationFragmentDirections
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.util.view.safeClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment<FragmentRegisterAccountBinding, RegisterViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_register_account
    override val viewModel: RegisterViewModel by viewModel()

    override fun handleEvents() {
        with(binding) {
            tvLogin.safeClick {
                findNavController().navigateUp()
            }
            btnRegister.safeClick {
                viewModel.registerUser(
                    name = edtName.text,
                    email = edtEmail.text,
                    password = edtPassword.text
                )
            }
        }
    }

    override fun observe() {
        with(viewModel) {
            registerSuccess.observe(viewLifecycleOwner, Observer {
                navRootController.navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToHomeFragment())
            })
            registerError.observe(viewLifecycleOwner, Observer {
                binding.edtName.errorMessage = it
            })
        }
    }
}