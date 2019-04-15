package com.rickshory.vegnab.extensions

import android.text.Editable

class ComponentExtensions {

    fun Editable.set(content: String) {
        clear()
        append(content)
    }

}