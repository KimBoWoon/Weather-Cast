package com.bowoon.android.compose.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.CallSuper

abstract class BaseActivity : ComponentActivity() {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}