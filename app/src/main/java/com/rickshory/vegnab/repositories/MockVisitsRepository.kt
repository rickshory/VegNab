package com.rickshory.vegnab.repositories

import android.util.Log
import com.rickshory.vegnab.models.Visit

class MockVisitsRepository : VisitsRepository {

    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val visits: MutableMap<Long, Visit> = HashMap()

    override fun getVisitById(visitId: Long): Visit? = visits[visitId]

    override fun saveVisit(item: Visit) {
        Log.d(TAG, "saving Visit ${item.id}")
        visits[item.id] = item
    }
}