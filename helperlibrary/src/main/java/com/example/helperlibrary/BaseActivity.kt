package com.example.helperlibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

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