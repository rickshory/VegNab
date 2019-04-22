package com.rickshory.vegnab.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Visits")
class Visit(@PrimaryKey @ColumnInfo(name = "id") val id: Long,
            @ColumnInfo(name = "name") val name: String,
            @ColumnInfo(name = "notes") val notes: String)
{
}