package com.nvqquy98.moneyqq.presentation.ui.base

import androidx.fragment.app.Fragment

interface OnDestinationChangedListener {
    fun onFragmentAttach(fragment : Fragment)

    fun onFragmentDetach(fragment: Fragment)
}
