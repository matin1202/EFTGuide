package com.matin.eftguide.exceptionHandlers

import android.app.Application
import android.util.Log


class EFTApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setCrashHandler()
    }


    private fun setCrashHandler() {
        Log.d("EFT app", "setCrashHandlers")
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(
            EFTExceptionHandler(
                this,
                defaultExceptionHandler!!
            )
        )
    }
}