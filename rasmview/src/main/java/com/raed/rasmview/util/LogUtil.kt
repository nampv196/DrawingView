package com.raed.rasmview.util

import android.util.Log

object LogUtil {

    private const val LOG_FLOW = "log_flow"

    fun logFlow(log: String){
        Log.i(LOG_FLOW, "flow: $log")
    }
}