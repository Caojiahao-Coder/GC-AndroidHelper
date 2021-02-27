package com.example.helperlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 一个基础Fragment基类 使用 viewBinding解决 大量的view注册代码
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected lateinit var mBinding: VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = getViewBinding()
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }


    protected abstract fun getViewBinding(): VB

    open fun initialize() {}

}