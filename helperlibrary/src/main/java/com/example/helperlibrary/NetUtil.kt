package com.example.helperlibrary

import android.os.Handler
import android.util.Log
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 一个网络请求帮助类
 */
object NetUtil {
    /**
     * 使用线程池
     */
    private val threadPoolExecutor: ThreadPoolExecutor =
        ThreadPoolExecutor(3, 5, 3, TimeUnit.SECONDS, LinkedBlockingDeque())
    private val ROOT: String = "http://10.0.2.2:2001/"
    private val handler: Handler = Handler()

    /**
     * 一个请求结束的回调接口
     */
    interface IOnHttpResultCallBack {
        /**
         * 请求成功
         */
        fun onSuccess(jsonResult: String)

        /**
         * 请求失败
         */
        fun onFailed(ex: Exception)
    }

    /**
     * 处理get 方法
     */
    fun get(url: String, prams: String?, callBack: IOnHttpResultCallBack) {
        threadPoolExecutor.execute {
            var address = ROOT + url

            if (prams != null) {
                address += "?$prams"
            }

            msg("============================")
            msg("GET")
            msg(address)

            try {
                val jsonResult: String =
                    Scanner(URL(address).openStream()).useDelimiter("\\A").next()
                msg(jsonResult)
                handler.post { callBack.onSuccess(jsonResult) }
            } catch (ex: Exception) {
                handler.post { callBack.onFailed(ex) }
            }
        }
    }

    /**
     * 处理 post 方法 支持 json和text两种传输方式
     */
    fun post(url: String, prams: String?, callback: IOnHttpResultCallBack) {
        threadPoolExecutor.execute {
            val address = ROOT + url

            msg("============================")
            msg("POST")
            msg(address)

            try {
                val http = URL(address).openConnection() as HttpURLConnection
                http.requestMethod = "POST"
                http.doOutput = true

                if (prams != null) {
                    if (prams.startsWith("{\""))
                        http.setRequestProperty("Content-Type", "application-json")
                    http.outputStream.write(prams.toByteArray())
                }

                val jsonResult = Scanner(http.inputStream).useDelimiter("\\A").next()
                msg(jsonResult)
                handler.post { callback.onSuccess(jsonResult) }
            } catch (ex: Exception) {
                handler.post { callback.onFailed(ex) }
            }
        }
    }

    /**
     * 一个ui线程上的log
     */
    private fun msg(content: String) {
        handler.post { Log.e("HTTP INFO", content) }
    }

}