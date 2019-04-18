package com.rickshory.vegnab.repositories

import com.rickshory.vegnab.models.Visit

class MockVisitsRepository : VisitsRepository {

    companion object {
        private val TAG = this::class.java.simpleName
        val instance = MockVisitsRepository()
    }

    private val visits: MutableMap<Long, Visit> = HashMap()

    override fun getVisitById(visitId: Long): Visit {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveVisit(item: Visit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}