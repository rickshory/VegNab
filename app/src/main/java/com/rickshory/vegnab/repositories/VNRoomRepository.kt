package com.rickshory.vegnab.repositories

import androidx.lifecycle.LiveData
import com.rickshory.vegnab.roomdb.daos.VisitDao
import com.rickshory.vegnab.roomdb.entities.Visit

class VNRoomRepository (private val visitDao: VisitDao) {
    val allVisits: LiveData<List<Visit>>
}