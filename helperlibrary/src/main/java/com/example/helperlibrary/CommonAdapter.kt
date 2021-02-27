package com.example.helperlibrary

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.viewbinding.ViewBinding

/**
 * @author Great Coder
 * @date  2021-02-27
 * @introduction 一个通用的ListView 适配器 使用 viewBinding 解决 view注册代码多的问题 但是只适用于 ListView GridView
 */
abstract class CommonAdapter<VB : ViewBinding, T>(private var lsData: MutableList<T>) :
    BaseAdapter() {

    lateinit var mBinding: VB
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mBinding = getViewBinding()
        viewBinding(position, lsData[position])
        return mBinding.root
    }

    override fun getItem(position: Int): T {
        return lsData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return lsData.size
    }

    protected abstract fun getViewBinding(): VB
    protected abstract fun viewBinding(position: Int, item: T)
}