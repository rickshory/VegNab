package com.rickshory.vegnab.extensions

import android.text.Editable

fun Editable.set(content: String) {
    clear()
    append(content)
}

class ComponentExtensions {

}