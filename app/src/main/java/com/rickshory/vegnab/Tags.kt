package com.rickshory.vegnab

object Tags {
    const val SPINNER_FIRST_USE = "FirstTime" // flag to catch and ignore erroneous first firing
    const val NEW_VISIT = "NewVisit"
    const val TEST_WEBVIEW = "TestWebview"
    const val WEBVIEW_TUTORIAL = "WebviewTutorial"
    const val WEBVIEW_PLOT_TYPES = "WebviewPlotTypes"
    const val WEBVIEW_REGIONAL_LISTS = "WebviewSppLists"
    object Fragments { // track which fragment is current
        const val VISITS_LIST = "VisitsList"
        const val VISIT_HEADER = "VisitHeader"
        const val DATA_SCREENS_CONTAINER = "DataScreensContainer"
        const val VEG_SUBPLOT = "VegSubplot"
        const val SELECT_SPECIES = "SelectSpecies"
        const val MANAGE_PLACEHOLDERS = "ManagePlaceholders"
        const val MANAGE_SPELLINGS = "ManageSpellings"
        const val CLOUD_STORAGE = "CloudStorage"
        const val EDIT_PLACEHOLDER = "EditPlaceholder"
        const val PLACEHOLDER_PIX_GRID = "PlaceholderPixGrid"
        const val PLACEHOLDER_PIC_DETAIL = "PlaceholderPicDetail"
    }
}