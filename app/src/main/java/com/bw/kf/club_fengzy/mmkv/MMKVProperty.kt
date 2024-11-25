package com.bw.kf.club_fengzy.mmkv

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MMKVProperty<T>(private val defValue: T): ReadWriteProperty<MMKVOwner ,T> {
    override fun getValue(thisRef: MMKVOwner, property: KProperty<*>): T {
        return thisRef.mmkv.run {
            when(defValue){
                is String -> getString(property.name, defValue)
                is Boolean -> getBoolean(property.name , defValue)
                is Long -> getLong(property.name , defValue)
                is Int -> getInt(property.name, defValue)
                is Float -> getFloat(property.name, defValue)
                is Double -> decodeDouble(property.name, defValue)
                else ->Unit
            }
        }as T
    }

    override fun setValue(thisRef: MMKVOwner, property: KProperty<*>, value: T) {
        thisRef.mmkv.run {
            when (value) {
                is String -> putString(property.name, value)
                is Boolean -> putBoolean(property.name, value)//false
                is Long -> putLong(property.name, value)
                is Int -> putInt(property.name, value)
                is Float -> putFloat(property.name, value)
                is Double -> encode(property.name, value)
                else -> Unit
            }
        }
    }
}