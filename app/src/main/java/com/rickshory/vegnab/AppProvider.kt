package com.rickshory.vegnab

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log

/**
 * Provider for the VegNab app
 * This is the only class that knows about [AppDatabase]
 * */
private const val TAG = "AppProvider"
const val CONTENT_AUTHORITY = "com.rickshory.vegnab.provider"
private const val PROJECTS = 100
private const val PROJECTS_ID = 102
private const val VISITS = 110
private const val VISITS_ID = 112
private const val VEGITEMS = 120
private const val VEGITEMS_ID = 122
private const val NAMERS = 130
private const val NAMERS_ID = 132

val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

class AppProvider: ContentProvider() {

    private val uriMatcher by lazy { buildUriMatcher() }

    private fun buildUriMatcher() : UriMatcher {
        Log.d(TAG, "buildUriMatcher: starts")
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI(CONTENT_AUTHORITY, Contract_Projects.TABLE_NAME, PROJECTS)
        matcher.addURI(CONTENT_AUTHORITY, "${Contract_Projects.TABLE_NAME}/#", PROJECTS_ID)

        matcher.addURI(CONTENT_AUTHORITY, Contract_Visits.TABLE_NAME, VISITS)
        matcher.addURI(CONTENT_AUTHORITY, "${Contract_Visits.TABLE_NAME}/#", VISITS_ID)

//        matcher.addURI(CONTENT_AUTHORITY, Contract_VegItems.TABLE_NAME, VEGITEMS)
//        matcher.addURI(CONTENT_AUTHORITY, "${Contract_VegItems.TABLE_NAME}/#", VEGITEMS_ID)
        matcher.addURI(CONTENT_AUTHORITY, Contract_Namers.TABLE_NAME, NAMERS)
        matcher.addURI(CONTENT_AUTHORITY, "${Contract_Namers.TABLE_NAME}/#", NAMERS_ID)
        return matcher
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate: starts")
        return true
    }

    override fun getType(uri: Uri): String? {
        val match = uriMatcher.match(uri)
        return when (match) {
            PROJECTS -> Contract_Projects.CONTENT_TYPE
            PROJECTS_ID -> Contract_Projects.CONTENT_ITEM_TYPE

            VISITS -> Contract_Projects.CONTENT_TYPE
            VISITS_ID -> Contract_Projects.CONTENT_ITEM_TYPE

            NAMERS -> Contract_Namers.CONTENT_TYPE
            NAMERS_ID -> Contract_Namers.CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("unknown uri: $uri")
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d(TAG, "query: called with uri $uri")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "query: match = $match")
        val queryBuilder = SQLiteQueryBuilder()
        when (match) {
            PROJECTS -> queryBuilder.tables = Contract_Projects.TABLE_NAME

            PROJECTS_ID -> {
                queryBuilder.tables = Contract_Projects.TABLE_NAME
                val id = Contract_Projects.getID(uri)
                queryBuilder.appendWhere("${Contract_Projects.Columns.ID} = ")
                queryBuilder.appendWhereEscapeString("$id")
            }

            VISITS -> queryBuilder.tables = Contract_Visits.TABLE_NAME

            VISITS_ID -> {
                queryBuilder.tables = Contract_Visits.TABLE_NAME
                val id = Contract_Visits.getID(uri)
                queryBuilder.appendWhere("${Contract_Visits.Columns.ID} = ")
                queryBuilder.appendWhereEscapeString("$id")
            }

            NAMERS -> queryBuilder.tables = Contract_Namers.TABLE_NAME

            NAMERS_ID -> {
                queryBuilder.tables = Contract_Namers.TABLE_NAME
                val id = Contract_Namers.getID(uri)
                queryBuilder.appendWhere("${Contract_Namers.Columns.ID} = ")
                queryBuilder.appendWhereEscapeString("$id")
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        val db = AppDatabase.getInstance(context).readableDatabase
        val cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        Log.d(TAG, "query: rows in returned cursor = ${cursor.count}") // TODO remove this line

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        Log.d(TAG, "insert: called with uri $uri")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "insert: match = $match")

        val recordID: Long
        val returnUri: Uri

        when (match) {
            PROJECTS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                recordID = db.insert(Contract_Projects.TABLE_NAME, null, values)
                if (recordID != -1L) {
                    returnUri = Contract_Projects.buildUriFromId(recordID)
                } else {
                    throw SQLException("Failed to insert. Uri was: $uri")
                }
            }

            VISITS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                recordID = db.insert(Contract_Visits.TABLE_NAME, null, values)
                if (recordID != -1L) {
                    returnUri = Contract_Visits.buildUriFromId(recordID)
                } else {
                    throw SQLException("Failed to insert. Uri was: $uri")
                }
            }

            NAMERS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                recordID = db.insert(Contract_Namers.TABLE_NAME, null, values)
                if (recordID != -1L) {
                    returnUri = Contract_Namers.buildUriFromId(recordID)
                } else {
                    throw SQLException("Failed to insert. Uri was: $uri")
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        Log.d(TAG, "Exiting insert, returning Uri: $returnUri")
        return returnUri
    }

    override fun update(uri: Uri, values: ContentValues, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "update: called with uri $uri")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "update: match = $match")

        var count: Int
        var selectionCriteria: String

        when (match) {

            PROJECTS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.update(Contract_Projects.TABLE_NAME, values, selection, selectionArgs)
            }

            PROJECTS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Projects.getID(uri)
                selectionCriteria = "${Contract_Projects.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.update(Contract_Projects.TABLE_NAME, values, selectionCriteria, selectionArgs)
            }

            VISITS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.update(Contract_Visits.TABLE_NAME, values, selection, selectionArgs)
            }

            VISITS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Visits.getID(uri)
                selectionCriteria = "${Contract_Visits.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.update(Contract_Visits.TABLE_NAME, values, selectionCriteria, selectionArgs)
            }

            NAMERS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.update(Contract_Namers.TABLE_NAME, values, selection, selectionArgs)
            }

            NAMERS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Namers.getID(uri)
                selectionCriteria = "${Contract_Namers.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.update(Contract_Namers.TABLE_NAME, values, selectionCriteria, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        Log.d(TAG, "done with update: count = $count")
        return count
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "delete: called with uri $uri")
        val match = uriMatcher.match(uri)
        Log.d(TAG, "delete: match = $match")

        var count: Int
        var selectionCriteria: String

        when (match) {

            PROJECTS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.delete(Contract_Projects.TABLE_NAME, selection, selectionArgs)
            }

            PROJECTS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Projects.getID(uri)
                selectionCriteria = "${Contract_Projects.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.delete(Contract_Projects.TABLE_NAME, selectionCriteria, selectionArgs)
            }

            VISITS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.delete(Contract_Visits.TABLE_NAME, selection, selectionArgs)
            }

            VISITS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Visits.getID(uri)
                selectionCriteria = "${Contract_Visits.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.delete(Contract_Visits.TABLE_NAME, selectionCriteria, selectionArgs)
            }

            NAMERS -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                count = db.delete(Contract_Namers.TABLE_NAME, selection, selectionArgs)
            }

            NAMERS_ID -> {
                val db = AppDatabase.getInstance(context).writableDatabase
                val id = Contract_Namers.getID(uri)
                selectionCriteria = "${Contract_Namers.Columns.ID}=$id"
                if (selection != null && selection.isNotEmpty()) {
                    selectionCriteria += " AND ($selection)"
                }
                count = db.delete(Contract_Namers.TABLE_NAME, selectionCriteria, selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        Log.d(TAG, "done with delete: count = $count")
        return count
    }
}