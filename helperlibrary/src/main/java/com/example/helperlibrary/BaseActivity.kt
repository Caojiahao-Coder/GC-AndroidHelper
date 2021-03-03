package com.example.helperlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 一个基础Activity基类 使用 viewBinding解决 大量的view注册代码
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var mBinding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = getViewBinding()
        setContentView(mBinding.root)
        initialize()


    }

    protected abstract fun getViewBinding(): VB
    open fun initialize() {}
}