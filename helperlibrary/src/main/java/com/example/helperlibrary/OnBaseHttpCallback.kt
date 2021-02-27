package com.example.helperlibrary

import android.util.Log
import java.lang.Exception

abstract class OnBaseHttpCallback : NetUtil.IOnHttpResultCallBack {
    override fun onFailed(ex: Exception) {
        Log.e("HTTP ERROR", ex.toString())
    }
}