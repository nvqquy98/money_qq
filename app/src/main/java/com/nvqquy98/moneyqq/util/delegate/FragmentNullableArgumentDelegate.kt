package com.nvqquy98.moneyqq.util.delegate

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class FragmentNullableArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T?> {
    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        if (thisRef.arguments == null) thisRef.arguments = Bundle()

        val args = thisRef.requireArguments()
        val key = property.name

        args.putAll(bundleOf(key to value))
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        val key = property.name
        return thisRef.arguments?.get(key) as? T
    }
}

fun <T : Any?> nullableArguments(): ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()
