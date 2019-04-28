package com.rickshory.vegnab.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rickshory.vegnab.repositories.VNRoomRepository
import com.rickshory.vegnab.roomdb.entities.Visit

class VNRoomViewModel (app: Application) : AndroidViewModel(app)  {
    private val repos: VNRoomRepository
    val allVisits: LiveData<List<Visit>>
}