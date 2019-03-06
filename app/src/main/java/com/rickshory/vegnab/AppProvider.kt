package com.rickshory.vegnab

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
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

val CONTENT_AUTHORITY_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")

class AppProvider: ContentProvider() {

    private val uriMatcher by lazy { buildUriMatcher() }

    private fun buildUriMatcher() : UriMatcher {
        Log.d(TAG, "buildUriMatcher: starts")
        val matcher = UriMatcher(UriMatcher.NO_MATCH)

        matcher.addURI(CONTENT_AUTHORITY, Contract_Projects.TABLE_NAME, PROJECTS)
        matcher.addURI(CONTENT_AUTHORITY, "${Contract_Projects.TABLE_NAME}/#", PROJECTS_ID)

//        matcher.addURI(CONTENT_AUTHORITY, Contract_Visits.TABLE_NAME, VISITS)
//        matcher.addURI(CONTENT_AUTHORITY, "${Contract_Visits.TABLE_NAME}/#", VISITS_ID)
//
//        matcher.addURI(CONTENT_AUTHORITY, Contract_VegItems.TABLE_NAME, VEGITEMS)
//        matcher.addURI(CONTENT_AUTHORITY, "${Contract_VegItems.TABLE_NAME}/#", VEGITEMS_ID)

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
                val projectID = Contract_Projects.getID(uri)
                queryBuilder.appendWhere("${Contract_Projects.Columns.ID} = ")
                queryBuilder.appendWhereEscapeString("$projectID")
            }

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        val db = AppDatabase.getInstance(context).readableDatabase
        val cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
        Log.d(TAG, "query: rows in returned cursor = ${cursor.count}") // TODO remove this line

        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(uri: Uri, values: ContentValues, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}