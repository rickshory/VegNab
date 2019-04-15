package com.rickshory.vegnab.models

import java.util.*

data class Visit2 (val noteId: String = UUID.randomUUID().toString())  {
    var name: String = ""
    var notes: String = ""
}
