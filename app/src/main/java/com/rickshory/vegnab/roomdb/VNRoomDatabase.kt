package com.rickshory.vegnab.roomdb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rickshory.vegnab.roomdb.daos.VisitDao
import com.rickshory.vegnab.roomdb.entities.Visit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import org.koin.dsl.context.Context

@Database(entities = arrayOf(Visit::class), version = 1)
public abstract class VNRoomDatabase: RoomDatabase() {
    private val TAG = this::class.java.simpleName
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
                ).addCallback(VNDatabaseCallback(scope)).build()
                    INSTANCE = instance
                return instance
            }
        }
    }

    private class VNDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        private val TAG = this::class.java.simpleName

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.visitDao())
                }
            }
        }

        fun populateDatabase(visitDao: VisitDao) {
            visitDao.deleteAll()
            Log.d(TAG, "adding record 1 to database")
            var visit = Visit(1, "tstVis", "test of Visit entry")
            visitDao.insert(visit)
            Log.d(TAG, "adding record 2 to database")
            visit = Visit(2, "secondVis", "another test")
            visitDao.insert(visit)
            Log.d(TAG, "adding record 3 to database")
            visit = Visit(3, "addlVis", "yet more")
            visitDao.insert(visit)
        }
    }


}



