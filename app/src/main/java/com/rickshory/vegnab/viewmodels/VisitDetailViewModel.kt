package com.rickshory.vegnab.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rickshory.vegnab.Visit

class VisitDetailViewModel : ViewModel() {
    companion object {
        private val TAG = this::class.java.simpleName
    }


    private val mutableVisitId: MutableLiveData<Long?> = MutableLiveData()
    val currentVisit: LiveData<Visit>


    init {
        currentVisit = Transformations.switchMap(mutableVisitId, { loadVisit(it!!) })
    }


    private fun loadVisit(visitId: Long): LiveData<Visit> {
        Log.d(TAG, "Loading visit")
        val visit = Visit("visit name", "visit notes", "visit location").apply {
            id = 0
        }
        return MutableLiveData<Visit>().apply { postValue(visit) }
    }


    fun setVisitId(id: Long) {
        Log.d(TAG, "VisitDetail of $id requested")
        mutableVisitId.postValue(id)
    }


    fun saveVisit(item: Visit) {
        Log.d(TAG, "Saving visit ${item.id}")
    }
}

