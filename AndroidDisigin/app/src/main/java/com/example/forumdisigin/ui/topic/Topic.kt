package com.example.forumdisigin.ui.topic

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forumdisigin.R
import kotlinx.android.synthetic.main.fragment_topic.*


class Topic : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_topic)

    }
}