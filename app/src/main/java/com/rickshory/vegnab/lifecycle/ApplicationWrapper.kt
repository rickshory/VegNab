package com.rickshory.vegnab.lifecycle

import android.app.Application

class ApplicationWrapper : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}