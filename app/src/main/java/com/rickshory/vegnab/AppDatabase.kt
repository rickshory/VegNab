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
        var sSQL = """
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

        sSQL = """
        CREATE TABLE ${Contract_Namers.TABLE_NAME} (
        ${Contract_Namers.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
        ${Contract_Namers.Columns.NAMERS_NAME} VARCHAR(10) NOT NULL
        CHECK (LENGTH(${Contract_Namers.Columns.NAMERS_NAME})>=2)
        );
        """.replaceIndent(" ")
        Log.d(TAG, "sSQL: $sSQL")
        db.execSQL(sSQL)

/*
CREATE TABLE IF NOT EXISTS "Visits" (
"_id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
"VisitName" VARCHAR(16) NOT NULL
CHECK (LENGTH(VisitName)>=2),
"VisitDate" TIMESTAMP NOT NULL, -- visible to user & can be manually changed
"ProjID" INTEGER NOT NULL,
"PlotTypeID" INTEGER NOT NULL,
"StartTime" TIMESTAMP NOT NULL DEFAULT (DATETIME('now')), -- maintained automatically
"LastChanged" TIMESTAMP NOT NULL DEFAULT (DATETIME('now')), -- maintained automatically
"NamerID" INTEGER NOT NULL,
"Scribe" VARCHAR(20), -- optional, if someone besides the Namer is entering data
"RefLocID" INTEGER, -- can not be required for valid record, but app will keep bugging user to get this
"RefLocIsGood" BOOL NOT NULL DEFAULT 0, -- allow to accept as good, even if accuracy is poor
"Azimuth" INTEGER -- if it applies to this plot type
CHECK ((Azimuth IS NULL) OR ((CAST(Azimuth AS INTEGER) == Azimuth)
AND (Azimuth >= 0) AND (Azimuth <= 360))),
"VisitNotes" VARCHAR(255), -- limit the length; some users would write a thesis
"DeviceType" INTEGER DEFAULT 1, -- 1=Unknown, 2=Android
"DeviceID" VARCHAR(40) NOT NULL,
"DeviceIDSource" VARCHAR(30) NOT NULL,
/*
"DeviceID" should be a unique identifier for the device this Visit is entered on.
It would be either from TelephonyManager.getDeviceId() (IMEI, MEID, ESN, etc.), or
else ANDROID_ID, or else a random number generated by UUID.
ESNs are either 11-digit decimal numbers or 8-digit hexadecimal numbers
MEIDs are 56 bits long, the same length as the IMEI
MEID allows hexadecimal digits while IMEI allows only decimal digits
IMEI length 17
MEID length 14
ANDROID_ID is a 64-bit number as a hex string, therefore length 16
UUID is 36 characters long
Use 40 to be sure the field is long enough.
"DeviceIDSource" is usually 'phone' or 'AndroidID' or 'UUID' but allowed to be anything
*/
"IsComplete" BOOL NOT NULL DEFAULT 0, -- flag to sync to cloud storage, if subscribed; option to automatically set following flag to 0 after sync
"ShowOnMobile" BOOL NOT NULL DEFAULT 1, -- allow masking out, to reduce clutter
"Include" BOOL NOT NULL DEFAULT 1, -- include in analysis, not used on mobile but here for completeness
"IsDeleted" BOOL NOT NULL DEFAULT 0, -- don't allow user to actually delete a visit, just flag it; need to keep all Visits for Placeholders
"NumAdditionalLocations" INTEGER NOT NULL DEFAULT 0, -- if additional locations are mapped, maintain the count
"AdditionalLocationsType" INTEGER NOT NULL DEFAULT 1 -- 1=points, 2=line, 3=polygon
CHECK ((AdditionalLocationsType >= 1) AND (AdditionalLocationsType <= 3)),
"AdditionalLocationSelected" INTEGER, -- the currently active additional location for this visit, if one is selected
FOREIGN KEY("ProjID") REFERENCES Projects("_id"),
FOREIGN KEY("PlotTypeID") REFERENCES PlotTypes("_id"),
FOREIGN KEY("NamerID") REFERENCES Namers("_id"),
FOREIGN KEY("RefLocID") REFERENCES Locations("_id"),
FOREIGN KEY("AdditionalLocationSelected") REFERENCES Locations("_id"),
FOREIGN KEY("AdditionalLocationsType") REFERENCES LocationTypes("_id")
);*/
        sSQL = """
        CREATE TABLE ${Contract_Visits.TABLE_NAME} (
        ${Contract_Visits.Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
        ${Contract_Visits.Columns.VISIT_NAME} VARCHAR(${Contract_Visits.Settings.VISIT_NAME_MAX_LENGTH}) NOT NULL
        CHECK (LENGTH(${Contract_Visits.Columns.VISIT_NAME})>=${Contract_Visits.Settings.VISIT_NAME_MIN_LENGTH}),
        ${Contract_Visits.Columns.VISIT_LOCATION} VARCHAR(50) NOT NULL,
        ${Contract_Visits.Columns.VISIT_NOTES} VARCHAR(255)
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