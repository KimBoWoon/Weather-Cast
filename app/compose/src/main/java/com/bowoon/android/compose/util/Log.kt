package com.bowoon.android.compose.util

import android.util.Log
import com.bowoon.android.compose.BuildConfig

object Log {
    fun i(msg: String) {
        if (BuildConfig.DEBUG) Log.i(tag(), msg)
    }

    fun i(msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) Log.i(tag(), msg, tr)
    }

    fun v(msg: String) {
        if (BuildConfig.DEBUG) Log.v(tag(), msg)
    }

    fun v(msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) Log.v(tag(), msg, tr)
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) Log.d(tag(), msg)
    }

    fun d(msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) Log.d(tag(), msg, tr)
    }

    fun w(msg: String) {
        if (BuildConfig.DEBUG) Log.w(tag(), msg)
    }

    fun w(msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) Log.w(tag(), msg, tr)
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) Log.e(tag(), msg)
    }

    fun e(msg: String, tr: Throwable?) {
        if (BuildConfig.DEBUG) Log.e(tag(), msg, tr)
    }

    private fun tag(): String? {
        Thread.currentThread().stackTrace[4]?.apply {
            val className = className.substring(className.lastIndexOf(".") + 1)
            val linkString = "(${fileName}:${lineNumber})"
            val pathString = "APP# $className.${methodName}"
            return pathString + linkString
//            return if (pathString.length + linkString.length > 80) {
//                pathString.substring(0, 80 - linkString.length) + "..." + linkString
//            } else {
//                pathString + linkString
//            }
        }
        return null
    }
}