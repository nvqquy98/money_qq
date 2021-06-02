package com.nvqquy98.moneyqq.util.permission

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.nvqquy98.moneyqq.util.view.showPermissionAlertDialog

class SinglePermissionObserver(
    private val owner: LifecycleOwner,
    private val permission: String,
    private val onDenied: (() -> Unit)? = null,
    private val onPermanentlyDenied: (() -> Unit)? = null,
    private val onGranted: (() -> Unit)? = null
) : DefaultLifecycleObserver {

    private val activity by lazy {
        when (owner) {
            is FragmentActivity -> {
                owner
            }
            is Fragment -> {
                owner.activity
            }
            else -> {
                null
            }
        }
    }

    init {
        when (owner) {
            is FragmentActivity -> {
                owner.lifecycle.addObserver(this)
            }
            is Fragment -> {
                owner.lifecycle.addObserver(this)
            }
        }
    }

    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null
    private var deniedPermanentlyCount = 0

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        if (!hasPermissions(activity!!, permission) &&
            !ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permission)
        ) {
            deniedPermanentlyCount++
        }
        requestPermissionLauncher =
            activity?.activityResultRegistry?.register("$permission ${owner::class.simpleName}",
                owner,
                ActivityResultContracts.RequestPermission(),
                ActivityResultCallback<Boolean> { isGranted: Boolean ->
                    if (isGranted) {
                        deniedPermanentlyCount = 0
                        onGranted?.invoke()
                    } else {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                activity!!,
                                permission
                            )
                        ) {
                            deniedPermanentlyCount++
                            if (deniedPermanentlyCount > 1) {
                                onPermanentlyDenied?.invoke()
                                    ?: activity?.showPermissionAlertDialog()
                                return@ActivityResultCallback
                            }
                        } else {
                            deniedPermanentlyCount = 0
                        }

                        onDenied?.invoke()
                    }
                })
    }

    override fun onDestroy(owner: LifecycleOwner) {
        requestPermissionLauncher?.unregister()
        super.onDestroy(owner)
    }

    fun requestPermission() {
        requestPermissionLauncher?.launch(permission)
    }
}
