package com.example.helperlibrary

import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONObject

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 一个Json帮助类 当前只能用于解析json 使用了Gson和反射
 */
object JsonHelper {
    /**
     * 解析json数组变成一个实体集合 使用了google的Gson
     */
    fun <T> jsonToList(json: String, cls: Class<T>): MutableList<T> {
        val lsData: MutableList<T> = ArrayList()

        JsonParser.parseString(json).asJsonArray.forEach {
            val obj: T = Gson().fromJson(it, cls)
            lsData.add(obj)
        }
        return lsData
    }

    /**
     * 用于解析json变成一个实体 使用反射
     */
    fun <T> jsonToObj(json: String, cls: Class<*>): T? {
        try {
            val instance = cls.newInstance()
            val jsonObject = JSONObject(json)
            val fields = instance.javaClass.fields
            for (field in fields) {
                try {
                    cls.getField(field.name)[instance] = jsonObject[field.name]
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            return instance as T
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}