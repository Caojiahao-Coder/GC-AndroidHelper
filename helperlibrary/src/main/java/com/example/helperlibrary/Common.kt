package com.example.helperlibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.io.ByteArrayOutputStream
import java.lang.Exception

object Common {


    /**
     * 一个时间选择完成的回调接口
     */
    interface IOnDateCallback {
        fun result(date: String)
    }

    /**
     * 使用 datePickerDialog 做时间选择 操作 提供 两种模式选择 一个是 - 一个是 /
     */
    fun selectDate(context: Context, dateType: DateType, callback: IOnDateCallback) {
        val line = if (dateType == DateType.LINE) "-" else "/"
        val datePickerDialog = DatePickerDialog(context)
        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            val date = "$year$line${
            if (month + 1 > 10) (month + 1) else "0" + (month + 1)
            }$line${if (dayOfMonth > 10) dayOfMonth else "0$dayOfMonth"}"
            callback.result(date = date)
        }
        datePickerDialog.show()
    }

    /**
     * 将bitmap 转为 Base64
     */
    fun bitmapToBase64(bitmap: Bitmap): String? {
        val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    /**
     * 将 Base64 转为 bitmap
     */
    fun base64ToBitmap(base64: String): Bitmap? {
        val bytes = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


    /**
     * 打开相机
     *
     * ！！ 需要网络权限！！！！！！
     */
    fun takePhoto(activity: Activity) {
        try {
            activity.startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 101)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * 打开相册
     */
    fun selectPhoto(activity: Activity) {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activity.startActivityForResult(intent, 102)
    }

    /**
     * 根据 Uri 提取 Bitmap
     */
    fun uriToBitmap(uri: Uri, context: Context): Bitmap {
        return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }

    /**
     * 绑定 Spinner数据
     */
    fun <T> bindSpinnerData(context: Context, spinner: Spinner, lsData: List<T>) {
        spinner.adapter =
            ArrayAdapter<T>(context, android.R.layout.simple_dropdown_item_1line, lsData)
    }

    /**
     * 写入 Sp数据 支持全部格式
     */
    @SuppressLint("CommitPrefEdits")
    fun spWriteData(context: Context, name: String, content: Any) {
        val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        when (content) {
            is String -> {
                editor.putString(name, content)
            }
            is Int -> {
                editor.putInt(name, content)
            }
            is Boolean -> {
                editor.putBoolean(name, content)
            }
            is Long -> {
                editor.putLong(name, content)
            }
            is Float -> {
                editor.putFloat(name, content)
            }
        }
    }

    /**
     * 读取 Sp数据 支持全部格式
     */
    fun spReadData(context: Context, name: String, content: Any): Any? {
        val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        when (content) {
            is String -> {
                return sharedPreferences.getString(name, content)
            }
            is Int -> {
                return sharedPreferences.getInt(name, content)
            }
            is Boolean -> {
                return sharedPreferences.getBoolean(name, content)
            }
            is Long -> {
                return sharedPreferences.getLong(name, content)
            }
            is Float -> {
                return sharedPreferences.getFloat(name, content)
            }
        }
        return null
    }
}