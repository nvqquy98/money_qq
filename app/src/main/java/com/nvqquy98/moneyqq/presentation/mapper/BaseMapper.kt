package com.nvqquy98.moneyqq.presentation.mapper

import com.nvqquy98.moneyqq.data.model.BaseData
import com.nvqquy98.moneyqq.presentation.model.BaseModel

abstract class BaseMapper<in T : BaseData, R : BaseModel> {
    abstract fun map(data: T): R

    open fun nullableMap(entity: T?): R? {
        return entity?.let { map(it) }
    }

    open fun map(dataCollection: Collection<T>): List<R> {
        return dataCollection.map { map(it) }
    }

    open fun nullableMap(dataCollection: Collection<T>?): List<R>? {
        return dataCollection?.map { map(it) }
    }
}
