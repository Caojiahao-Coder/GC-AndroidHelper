package com.example.gc_androidhelper

import android.graphics.Color
import android.util.Log
import android.view.View
import com.example.gc_androidhelper.databinding.ActivityMainBinding
import com.example.helperlibrary.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var binding: ActivityMainBinding
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        binding = mBinding

        binding.tvMain.text = "Good"

        binding.tvMain.setTextColor(Color.BLUE)
        binding.tvMain.setBackgroundColor(Color.RED)

        binding.tvMain.textSize = 32f

        //验证一些特殊的函数时候构建正确
        binding.tvMain.setOnClickListener {
            Common.selectDate(this, DateType.LINE, object : Common.IOnDateCallback {
                override fun result(date: String) {
                    binding.tvMain.text = date
                }
            })
        }
    }

    private fun getUserData(view: View) {
        NetUtil.get("api/GetUserInfo", null, object : OnBaseHttpCallback() {
            override fun onSuccess(jsonResult: String) {
                val lsUserData: MutableList<UserInfo> =
                    JsonHelper.jsonToList(jsonResult, UserInfo::class.java)

                lsUserData.forEach { Log.e("UserData", it.toString()) }
            }
        })
    }
}