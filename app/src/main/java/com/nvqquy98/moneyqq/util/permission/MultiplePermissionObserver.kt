package com.nvqquy98.moneyqq.util.permission

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MultiplePermissionObserver(
    private val activityOrFragment: Any,
    private val permissions: Array<String>,
    private val onResult: ((Map<String, Boolean>) -> Unit)? = null
) : DefaultLifecycleObserver {

    private val activity by lazy {
        when (activityOrFragment) {
            is FragmentActivity -> {
                activityOrFragment
            }
            is Fragment -> {
                activityOrFragment.activity
            }
            else -> {
                null
            }
        }
    }

    init {
        when (activityOrFragment) {
            is FragmentActivity -> {
                activityOrFragment.lifecycle.addObserver(this)
            }
            is Fragment -> {
                activityOrFragment.lifecycle.addObserver(this)
            }
        }
    }

    private var requestPermissionLauncher: ActivityResultLauncher<Array<String>>? = null

    override fun onCreate(owner: LifecycleOwner) {
        requestPermissionLauncher =
            activity?.activityResultRegistry?.register(permissions.toString(),
                owner,
                ActivityResultContracts.RequestMultiplePermissions(),
                ActivityResultCallback<Map<String, Boolean>> { result ->
                    onResult?.invoke(result)
                })
    }

    override fun onDestroy(owner: LifecycleOwner) {
        requestPermissionLauncher?.unregister()
        super.onDestroy(owner)
    }

    fun requestPermissions() {
        requestPermissionLauncher?.launch(permissions)
    }
}
