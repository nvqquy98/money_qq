package com.nvqquy98.moneyqq.data.reponsitory.source.local.api.pref

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class PreferenceLiveData<T : Any?>(
    val sharedPrefs: SharedPreferences,
    private val key: String,
    private val defaultValue: T,
    initValue: T
) : LiveData<T>(initValue) {

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == this.key) {
                value = getValueFromPreferences(key, defaultValue)
            }
        }

    abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }
}

class PreferenceIntLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Int) :
    PreferenceLiveData<Int>(sharedPrefs, key, defValue, sharedPrefs.getInt(key, defValue)) {
    override fun getValueFromPreferences(key: String, defValue: Int): Int =
        sharedPrefs.getInt(key, defValue)
}

class PreferenceStringLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: String
) :
    PreferenceLiveData<String>(
        sharedPrefs,
        key,
        defValue,
        sharedPrefs.getString(key, defValue) ?: ""
    ) {
    override fun getValueFromPreferences(key: String, defValue: String): String =
        sharedPrefs.getString(key, defValue) ?: ""
}

class PreferenceBooleanLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Boolean
) :
    PreferenceLiveData<Boolean>(sharedPrefs, key, defValue, sharedPrefs.getBoolean(key, defValue)) {
    override fun getValueFromPreferences(key: String, defValue: Boolean): Boolean =
        sharedPrefs.getBoolean(key, defValue)
}

class PreferenceFloatLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Float) :
    PreferenceLiveData<Float>(sharedPrefs, key, defValue, sharedPrefs.getFloat(key, defValue)) {
    override fun getValueFromPreferences(key: String, defValue: Float): Float =
        sharedPrefs.getFloat(key, defValue)
}

class PreferenceLongLiveData(sharedPrefs: SharedPreferences, key: String, defValue: Long) :
    PreferenceLiveData<Long>(sharedPrefs, key, defValue, sharedPrefs.getLong(key, defValue)) {
    override fun getValueFromPreferences(key: String, defValue: Long): Long =
        sharedPrefs.getLong(key, defValue)
}

class PreferenceJsonLiveData<T>(
    sharedPrefs: SharedPreferences,
    key: String,
    private val gson: Gson,
    private val clazz: Class<T>
) : PreferenceLiveData<T?>(
    sharedPrefs, key, null,
    gson.fromJson(sharedPrefs.getString(key, ""), clazz)
) {
    override fun getValueFromPreferences(key: String, defValue: T?): T? =
        gson.fromJson(sharedPrefs.getString(key, ""), clazz)
}

class PreferenceJsonListLiveData<T>(
    sharedPrefs: SharedPreferences,
    key: String,
    private val gson: Gson,
    private val clazz: Class<T>
) : PreferenceLiveData<List<T>?>(
    sharedPrefs, key, null,
    gson.fromJson<List<T>>(
        sharedPrefs.getString(key, ""),
        TypeToken.getParameterized(List::class.java, clazz).type
    )
) {

    override fun getValueFromPreferences(key: String, defValue: List<T>?): List<T>? {
        return gson.fromJson<List<T>>(
            sharedPrefs.getString(key, ""),
            TypeToken.getParameterized(List::class.java, clazz).type
        )
    }
}

class PreferenceStringSetLiveData(
    sharedPrefs: SharedPreferences,
    key: String,
    defValue: Set<String>
) :
    PreferenceLiveData<Set<String>>(
        sharedPrefs, key, defValue,
        sharedPrefs.getStringSet(key, defValue) ?: emptySet()
    ) {
    override fun getValueFromPreferences(key: String, defValue: Set<String>): Set<String> =
        sharedPrefs.getStringSet(key, defValue) ?: emptySet()
}

fun SharedPreferences.intLiveData(key: String, defValue: Int): PreferenceLiveData<Int> {
    return PreferenceIntLiveData(
        this,
        key,
        defValue
    )
}

fun SharedPreferences.stringLiveData(
    key: String,
    defValue: String
): PreferenceLiveData<String> {
    return PreferenceStringLiveData(
        this,
        key,
        defValue
    )
}

fun SharedPreferences.booleanLiveData(
    key: String,
    defValue: Boolean
): PreferenceLiveData<Boolean> {
    return PreferenceBooleanLiveData(
        this,
        key,
        defValue
    )
}

fun SharedPreferences.floatLiveData(key: String, defValue: Float): PreferenceLiveData<Float> {
    return PreferenceFloatLiveData(
        this,
        key,
        defValue
    )
}

fun SharedPreferences.longLiveData(key: String, defValue: Long): PreferenceLiveData<Long> {
    return PreferenceLongLiveData(
        this,
        key,
        defValue
    )
}

fun SharedPreferences.stringSetLiveData(
    key: String,
    defValue: Set<String>
): PreferenceLiveData<Set<String>> {
    return PreferenceStringSetLiveData(
        this,
        key,
        defValue
    )
}

fun <T> SharedPreferences.jsonLiveData(
    key: String,
    gson: Gson,
    clazz: Class<T>
): PreferenceJsonLiveData<T> {
    return PreferenceJsonLiveData(
        this,
        key,
        gson,
        clazz
    )
}

fun <T> SharedPreferences.jsonListLiveData(
    key: String,
    gson: Gson,
    clazz: Class<T>
): PreferenceJsonListLiveData<T> {
    return PreferenceJsonListLiveData(
        this,
        key,
        gson,
        clazz
    )
}
