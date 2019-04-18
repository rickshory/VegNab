package com.rickshory.vegnab.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rickshory.vegnab.models.Visit
import com.rickshory.vegnab.repositories.MockVisitsRepository

class VisitDetailViewModel : ViewModel() {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val mutableVisitId: MutableLiveData<Long?> = MutableLiveData()
    private val repository = MockVisitsRepository.instance
    val currentVisit: LiveData<Visit>

    init {
        currentVisit = Transformations.switchMap(mutableVisitId, { loadVisit(it!!) })
    }

    private fun loadVisit(visitId: Long): LiveData<Visit> {
        Log.d(TAG, "Loading visit")
        var newVisit = Visit("visit name", "visit notes", "visit location").apply {
            id = 0}
        val visit: Visit = if (visitId == null) newVisit else repository.getVisitById(visitId) ?: newVisit
        return MutableLiveData<Visit>().apply { postValue(visit) }
    }

    fun setVisitId(id: Long) {
        Log.d(TAG, "VisitDetail of $id requested")
        mutableVisitId.postValue(id)
    }

    fun saveVisit(item: Visit) {
        Log.d(TAG, "Saving visit ${item.id}")
        repository.saveVisit(item)
        if (mutableVisitId.value != item.id) setVisitId(item.id)
    }
}

