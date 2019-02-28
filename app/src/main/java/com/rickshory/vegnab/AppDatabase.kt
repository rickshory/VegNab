package com.rickshory.vegnab

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.IllegalStateException

/**
 * Created by Rick Shory 2019-02-25
 *
 * Basic database class for the application
 *
 * The only class that should use this is [AppProvider].
 *
 */

private const val TAG = "AppDatabase"
private const val DATABASE_NAME = "VegNab.db"
private const val DATABASE_VERSION = 3

internal class AppDatabase private constructor(context: Context ):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDatabase initializing")
    }

    override fun onCreate(db: SQLiteDatabase) {
        //CREATE TABLE "Projects" (
        //"_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
        //"ProjCode" VARCHAR(10) NOT NULL
        //CHECK (LENGTH(ProjCode)>=2),
        //"Description" VARCHAR(255),
        //"Context" VARCHAR(255),
        //"Caveats" VARCHAR(255),
        //"ContactPerson" VARCHAR(255),
        //"StartDate" DATE DEFAULT (DATETIME('now')),
        //"EndDate" DATE,
        //"HideOnMobile" BOOL NOT NULL DEFAULT 0, -- reduce clutter on mobile device
        //"IsDeleted" BOOL NOT NULL DEFAULT 0 -- don't allow user to actually delete a project, just flag it; need to keep for species Placeholders
        //)
        Log.d(TAG, "OnCreate: starts")
        val sSQL = """
        CREATE TABLE ${Contract_Projects.TABLE_NAME} (
        ${Contract_Projects.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
        ${Contract_Projects.Columns.PROJECT_CODE} VARCHAR(10) NOT NULL
        CHECK (LENGTH(${Contract_Projects.Columns.PROJECT_CODE})>=2),
        ${Contract_Projects.Columns.PROJECT_DESCRIPTION} VARCHAR(255),
        ${Contract_Projects.Columns.PROJECT_CONTEXT} VARCHAR(255),
        ${Contract_Projects.Columns.PROJECT_CAVEATS} VARCHAR(255),
        ${Contract_Projects.Columns.PROJECT_CONTACT_PERSON} VARCHAR(255),
        ${Contract_Projects.Columns.PROJECT_START_DATE} DATE DEFAULT (DATETIME('now')),
        ${Contract_Projects.Columns.PROJECT_END_DATE} DATE,
        ${Contract_Projects.Columns.PROJECT_HIDE_ON_MOBILE} BOOL NOT NULL DEFAULT 0, -- reduce clutter on mobile device
        ${Contract_Projects.Columns.PROJECT_IS_DELETED} BOOL NOT NULL DEFAULT 0 -- don't allow user to actually delete a project, just flag it; need to keep for species Placeholders
        );
        """.replaceIndent(" ")
        Log.d(TAG, "sSQL: $sSQL")
        db.execSQL(sSQL)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade: starts")
        when(newVersion) {
            1 -> {
                // upgrade to version 1
            }
            else -> throw IllegalStateException("onUpgrade with unknown newVersion: $newVersion")
        }
    }

    companion object : SingletonHolder<AppDatabase, Context>(::AppDatabase)

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//                instance ?: synchronized(this) {
//                    instance ?: AppDatabase(context).also {
//                        instance = it
//                    }
//                }
//
//    }
}