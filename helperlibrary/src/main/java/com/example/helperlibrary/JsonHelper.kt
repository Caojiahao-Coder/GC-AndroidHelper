package com.example.helperlibrary

import com.google.gson.Gson
import com.google.gson.JsonParser
import java.util.*
import kotlin.collections.ArrayList

object JsonHelper {
    fun <T> jsonToList(json: String, cls: Class<T>): MutableList<T> {
        val lsData: MutableList<T> = ArrayList()

        JsonParser.parseString(json).asJsonArray.forEach {
            val obj: T = Gson().fromJson(it, cls)
            lsData.add(obj as T)
        }
        return lsData
    }
}