package com.rickshory.vegnab.lifecycle

import android.app.Application
import com.rickshory.vegnab.repositories.MockVisitsRepository
import com.rickshory.vegnab.repositories.VisitsRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class ApplicationWrapper : Application() {
    companion object {
        private val modules : Module = applicationContext {
            bean { MockVisitsRepository() as VisitsRepository }
            
        }
    }
    override fun onCreate() {
        super.onCreate()
    }
}