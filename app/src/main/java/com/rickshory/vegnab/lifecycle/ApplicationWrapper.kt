package com.rickshory.vegnab.lifecycle

import android.app.Application
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class ApplicationWrapper : Application() {
    companion object {
        private val modules : Module = applicationContext {  }
    }
    override fun onCreate() {
        super.onCreate()
    }
}