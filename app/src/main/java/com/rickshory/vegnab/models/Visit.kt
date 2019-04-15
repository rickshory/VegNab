package com.rickshory.vegnab.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Visit(val name: String, val notes: String, val location: String) : Parcelable{
    var id: Long = 0
}