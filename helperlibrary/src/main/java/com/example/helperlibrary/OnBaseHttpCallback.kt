package com.example.helperlibrary

import android.util.Log
import java.lang.Exception

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 解决掉http 请求完成后的接口回调操作时的 失败函数冗余
 */
abstract class OnBaseHttpCallback : NetUtil.IOnHttpResultCallBack {
    override fun onFailed(ex: Exception) {
        //更多处理方式需要在更新
        Log.e("HTTP ERROR", ex.toString())
    }
}