package com.rickshory.vegnab

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns

object Contract_Namers {
    internal const val TABLE_NAME = "Namers"

    /**
     * The URI to access the Projects table
     * */
    val CONTENT_URI: Uri = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME)
    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

    // Projects fields
    object Columns {
        const val ID = BaseColumns._ID
        const val NAMERS_NAME = "NamerName"
    }

    fun getID(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id:Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}