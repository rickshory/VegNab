package com.rickshory.vegnab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NewVisitActivity : AppCompatActivity() {
    private lateinit var editVisitNameView: TextView
    private lateinit var editVisitDescriptionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_visit)
    }
}
