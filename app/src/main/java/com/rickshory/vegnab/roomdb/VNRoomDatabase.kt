package com.rickshory.vegnab.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rickshory.vegnab.roomdb.daos.VisitDao
import com.rickshory.vegnab.roomdb.entities.Visit
import kotlinx.coroutines.CoroutineScope

//import org.koin.dsl.context.Context

@Database(entities = arrayOf(Visit::class), version = 1)
public abstract class VNRoomDatabase: RoomDatabase() {

    abstract fun visitDao(): VisitDao

    companion object {
        @Volatile
        private var INSTANCE: VNRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): VNRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) { // if existing, lock
                // else create instance
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VNRoomDatabase::class.java,
                    "VN_database"
                ).build()
                    INSTANCE = instance
                return instance
            }
        }
    }
}

private class VNDatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {}

