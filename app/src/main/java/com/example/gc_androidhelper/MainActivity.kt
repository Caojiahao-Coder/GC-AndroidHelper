package com.example.gc_androidhelper

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.tvMain.setOnClickListener { Common.takePhoto(this@MainActivity) }
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