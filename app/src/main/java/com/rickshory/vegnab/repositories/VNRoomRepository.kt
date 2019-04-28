package com.rickshory.vegnab.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.rickshory.vegnab.roomdb.daos.VisitDao
import com.rickshory.vegnab.roomdb.entities.Visit

class VNRoomRepository (private val visitDao: VisitDao) {
    val allVisits: LiveData<List<Visit>> = visitDao.getAllVisits()

    @WorkerThread
    suspend fun insert(visit: Visit) {
        visitDao.insert(visit)
    }
}