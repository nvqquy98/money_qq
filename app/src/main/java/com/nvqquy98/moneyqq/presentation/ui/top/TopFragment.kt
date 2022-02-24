package com.nvqquy98.moneyqq.presentation.ui.top

import com.nvqquy98.moneyqq.R
import com.nvqquy98.moneyqq.databinding.FragmentTopBinding
import com.nvqquy98.moneyqq.presentation.ui.base.BaseFragment
import com.nvqquy98.moneyqq.presentation.ui.main.HomeViewModel
import com.nvqquy98.moneyqq.util.view.safeClick
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopFragment : BaseFragment<FragmentTopBinding, TopViewModel>() {
    override val layoutId: Int = R.layout.fragment_top
    override val viewModel: TopViewModel by viewModel()
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun handleEvents() {
        binding.btnLogout.safeClick {
            homeViewModel.logout()
        }
    }
}
