package com.speedchecker.android.sdk.demo

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.speedchecker.android.sdk.SpeedcheckerSDK

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        SpeedcheckerSDK.setLicenseKey(this, "Insert your key here")
    }
}