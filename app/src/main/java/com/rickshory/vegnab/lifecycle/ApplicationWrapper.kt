package com.rickshory.vegnab.lifecycle

import android.app.Application
import com.rickshory.vegnab.repositories.MockVisitsRepository
import com.rickshory.vegnab.repositories.VisitsRepository
import com.rickshory.vegnab.viewmodels.VisitDetailViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class ApplicationWrapper : Application() {
    companion object {
        private val modules : Module = applicationContext {
            bean { MockVisitsRepository() as VisitsRepository }
            viewModel { VisitDetailViewModel(get()) }
        }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(modules))
    }
}