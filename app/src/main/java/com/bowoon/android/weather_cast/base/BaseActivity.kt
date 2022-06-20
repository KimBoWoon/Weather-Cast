package com.bowoon.android.weather_cast.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>(
    private val layoutId: Int
) : ComponentActivity() {
    protected val binding: T by lazy {
        DataBindingUtil.setContentView(this, layoutId)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun initBinding()
    abstract fun initLiveData()
}