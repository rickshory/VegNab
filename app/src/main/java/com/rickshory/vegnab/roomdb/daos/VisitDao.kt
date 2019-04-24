package com.rickshory.vegnab.roomdb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rickshory.vegnab.roomdb.entities.Visit

@Dao
interface VisitDao {

    @Query("SELECT * from Visits")
    fun getAllVisits(): List<Visit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(visit: Visit)

    @Query("DELETE FROM Visits")
    fun deleteAll()
}