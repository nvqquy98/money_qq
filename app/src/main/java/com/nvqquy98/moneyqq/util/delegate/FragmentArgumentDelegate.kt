package com.nvqquy98.moneyqq.util.delegate

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class FragmentArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T> {

    var value: T? = null

    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (value == null) {
            val args = thisRef.arguments
                ?: throw  IllegalStateException("Cannot read property ${property.name} if no arguments have been set")

            value = args.get(property.name) as T
        }
        return value ?: throw  IllegalStateException("Property ${property.name} could not be read")
    }

    override operator fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        if (thisRef.arguments == null) thisRef.arguments = Bundle()

        val args = thisRef.requireArguments()
        val key = property.name

        args.putAll(bundleOf(key to value))
    }

}

fun <T : Any?> argument(): ReadWriteProperty<Fragment, T> = FragmentArgumentDelegate()
