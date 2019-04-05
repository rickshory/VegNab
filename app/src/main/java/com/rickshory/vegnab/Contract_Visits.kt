package com.rickshory.vegnab

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object Contract_Visits {
    internal const val TABLE_NAME = "Visits"

    /**
     * The URI to access the table
     * */
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)
    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

    // the fields
    object Columns {
        const val ID = BaseColumns._ID
        const val VISIT_NAME = ""
        const val VISIT_LOCATION = "Location"
        const val VISIT_NOTES = "Notes"
    }

    fun getID(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id:Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}