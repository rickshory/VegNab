package com.rickshory.vegnab

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

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

internal class AppDatabase constructor(context: Context ):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDatabase initializing")
    }

    override fun onCreate(db: SQLiteDatabase?) {
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
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}