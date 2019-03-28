package com.rickshory.vegnab

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class VisitsListOpts(val userID: String, val defaultProject: String, val defaultPlotType: String) : Parcelable{
    var id: Long = 0
}