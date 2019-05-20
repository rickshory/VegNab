package com.rickshory.vegnab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_new_visit.*

class NewVisitActivity : AppCompatActivity() {
    private lateinit var editVisitNameView: TextView
    private lateinit var editVisitDescriptionView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_visit)
        editVisitNameView = edit_visit_name
    }
}