package com.rickshory.vegnab

import android.os.Parcelable

@Parcelize
data class Visit(var id: Long, val name: String, val notes: String, val location: String) : Parcelable{
}