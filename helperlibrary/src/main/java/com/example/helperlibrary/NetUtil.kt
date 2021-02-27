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

object NetUtil {
    private val threadPoolExecutor: ThreadPoolExecutor =
        ThreadPoolExecutor(3, 5, 3, TimeUnit.SECONDS, LinkedBlockingDeque())
    private val ROOT: String = "http://10.0.2.2:2001/"
    private val handler: Handler = Handler()

    interface IOnHttpResultCallBack {
        fun onSuccess(jsonResult: String)
        fun onFailed(ex: Exception)
    }

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

    private fun msg(content: String) {
        handler.post { Log.e("HTTP INFO", content) }
    }

}