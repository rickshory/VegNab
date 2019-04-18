package com.rickshory.vegnab.repositories

import com.rickshory.vegnab.models.Visit

interface VisitsRepository {
    fun getVisitById(visitId : Long) : Visit?
    fun saveVisit(item: Visit)
}