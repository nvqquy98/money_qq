package com.nvqquy98.moneyqq.util.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.nvqquy98.moneyqq.util.common.Quadruple

fun <A, B> zipLiveData(
    a: LiveData<A>,
    b: LiveData<B>
): LiveData<Pair<A?, B?>> {
    return MediatorLiveData<Pair<A?, B?>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            this.value = Pair(localLastA, localLastB)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }
}

fun <A, B, C> zipLiveData(
    a: LiveData<A>,
    b: LiveData<B>,
    c: LiveData<C>
): LiveData<Triple<A?, B?, C?>> {
    return MediatorLiveData<Triple<A?, B?, C?>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            val localLastC = lastC
            this.value = Triple(localLastA, localLastB, localLastC)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
        addSource(c) {
            lastC = it
            update()
        }
    }
}

fun <A, B, C, D> zipLiveData(
    a: LiveData<A>,
    b: LiveData<B>,
    c: LiveData<C>,
    d: LiveData<D>
): LiveData<Quadruple<A?, B?, C?, D?>> {
    return MediatorLiveData<Quadruple<A?, B?, C?, D?>>().apply {
        var lastA: A? = null
        var lastB: B? = null
        var lastC: C? = null
        var lastD: D? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            val localLastC = lastC
            val localLastD = lastD
            this.value = Quadruple(localLastA, localLastB, localLastC, localLastD)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
        addSource(c) {
            lastC = it
            update()
        }
        addSource(d) {
            lastD = it
            update()
        }
    }
}

var <T> MutableLiveData<T>.valueIfDifferent: T?
    get() = null
    set(value) {
        if (this.value != value) {
            this.postValue(value)
        }
    }

fun <T> LiveData<T>.toSingleLiveEvent(): LiveData<T> {
    val result = SingleLiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}

val <reified T> LiveData<T>.nonNullValue: T
    inline get() = when (T::class) {
        Byte::class -> value ?: Byte.MIN_VALUE as T
        Short::class -> value ?: Short.MIN_VALUE as T
        Int::class -> value ?: Int.MIN_VALUE as T
        Long::class -> value ?: Long.MIN_VALUE as T
        Float::class -> value ?: Float.MIN_VALUE as T
        Double::class -> value ?: Double.MIN_VALUE as T
        String::class -> value.toString() as T
        else -> value as T
    }
