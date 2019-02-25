package com.rickshory.vegnab

import android.provider.BaseColumns

object Contract_Projects {
    internal const val TABLE_NAME = "Projects"
    // Projects fields
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
}