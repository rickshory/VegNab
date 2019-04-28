package com.rickshory.vegnab.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rickshory.vegnab.repositories.VNRoomRepository

class VNRoomViewModel (app: Application) : AndroidViewModel(app)  {
    private val repos: VNRoomRepository
}