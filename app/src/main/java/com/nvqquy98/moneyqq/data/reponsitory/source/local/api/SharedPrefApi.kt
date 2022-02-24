package com.nvqquy98.moneyqq.data.reponsitory.source.local.api

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.nvqquy98.moneyqq.data.reponsitory.source.local.api.pref.*
import com.nvqquy98.moneyqq.util.Constants.INVALID_FLOAT
import com.nvqquy98.moneyqq.util.Constants.INVALID_INT
import com.nvqquy98.moneyqq.util.Constants.INVALID_LONG

class SharedPrefApi(context: Context, val gson: Gson) {

    val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(SharedPrefKey.PREFS_NAME, Context.MODE_PRIVATE)
    }

    operator fun <T> set(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            else -> editor.putString(key, gson.toJson(data))
        }
        editor.apply()
    }

    @Throws(JsonSyntaxException::class)
    inline operator fun <reified T> get(key: String, default: T? = null): T {
        return when (T::class) {
            String::class -> sharedPreferences.getString(key, default as? String ?: "") as T
            Boolean::class -> sharedPreferences.getBoolean(key, default as? Boolean ?: false) as T
            Float::class -> sharedPreferences.getFloat(key, default as? Float ?: INVALID_FLOAT) as T
            Int::class -> sharedPreferences.getInt(key, default as? Int ?: INVALID_INT) as T
            Long::class -> sharedPreferences.getLong(key, default as? Long ?: INVALID_LONG) as T
            else -> gson.fromJson(sharedPreferences.getString(key, ""), T::class.java)
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> getAsLiveData(key: String, default: T? = null): LiveData<T> {
        return when (T::class) {
            String::class -> sharedPreferences.stringLiveData(key, default as? String ?: "")
            Boolean::class -> sharedPreferences.booleanLiveData(key, default as? Boolean ?: false)
            Float::class -> sharedPreferences.floatLiveData(key, default as? Float ?: INVALID_FLOAT)
            Int::class -> sharedPreferences.intLiveData(key, default as? Int ?: INVALID_INT)
            Long::class -> sharedPreferences.longLiveData(key, default as? Long ?: INVALID_LONG)
            else -> sharedPreferences.jsonLiveData(key, gson, T::class.java)
        } as LiveData<T>
    }

    inline fun <reified T> getList(key: String): List<T> {
        val typeOfT = TypeToken.getParameterized(List::class.java, T::class.java).type
        return gson.fromJson(get<String>(key), typeOfT) ?: emptyList()
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T> getListAsLiveData(key: String): LiveData<List<T>?> {
        return sharedPreferences.jsonListLiveData(key, gson, T::class.java)
    }

    fun removeKey(vararg key: String) {
        key.forEach {
            sharedPreferences.edit().remove(it).apply()
        }
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}