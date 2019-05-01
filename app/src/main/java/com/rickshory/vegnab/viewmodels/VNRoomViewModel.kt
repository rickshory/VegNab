package com.rickshory.vegnab.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rickshory.vegnab.repositories.VNRoomRepository
import com.rickshory.vegnab.roomdb.VNRoomDatabase
import com.rickshory.vegnab.roomdb.entities.Visit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class VNRoomViewModel (app: Application) : AndroidViewModel(app)  {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repo: VNRoomRepository
    val allVis: LiveData<List<Visit>>

    init {
        val visitsDao = VNRoomDatabase.getDatabase(app).visitDao()
        repo = VNRoomRepository(visitsDao)
        allVis = repo.allVisits
    }

    fun insert(visit: Visit) = scope.launch(Dispatchers.IO) {
        
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}