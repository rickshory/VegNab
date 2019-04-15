package com.rickshory.vegnab.contracts

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import com.rickshory.vegnab.CONTENT_AUTHORITY
import com.rickshory.vegnab.CONTENT_AUTHORITY_URI

object Contract_Projects {
    internal const val TABLE_NAME = "Projects"

    /**
     * The URI to access the table
     * */
    val CONTENT_URI: Uri = Uri.withAppendedPath(
        CONTENT_AUTHORITY_URI,
        TABLE_NAME
    )
    const val CONTENT_TYPE = "vnd.android.cursor.dir/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"
    const val CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$CONTENT_AUTHORITY.$TABLE_NAME"

    // the fields
    object Columns {
        const val ID = BaseColumns._ID
        const val PROJECT_CODE = "ProjCode"
        const val PROJECT_DESCRIPTION = "Description"
        const val PROJECT_CONTEXT = "Context"
        const val PROJECT_CAVEATS = "Caveats"
        const val PROJECT_CONTACT_PERSON = "ContactPerson"
        const val PROJECT_START_DATE = "StartDate"
        const val PROJECT_END_DATE = "EndDate"
        const val PROJECT_HIDE_ON_MOBILE = "HideOnMobile"
        const val PROJECT_IS_DELETED = "IsDeleted"
    }

    fun getID(uri: Uri): Long {
        return ContentUris.parseId(uri)
    }

    fun buildUriFromId(id:Long): Uri {
        return ContentUris.withAppendedId(CONTENT_URI, id)
    }
}