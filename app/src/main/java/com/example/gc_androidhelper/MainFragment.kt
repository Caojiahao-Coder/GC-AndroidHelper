package com.example.gc_androidhelper

import android.widget.Toast
import com.example.gc_androidhelper.databinding.FragmentMainBinding
import com.example.helperlibrary.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {
    lateinit var binding: FragmentMainBinding
    override fun getViewBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun initialize() {
        super.initialize()

        Toast.makeText(context, "Hello World", Toast.LENGTH_SHORT).show()
    }

}