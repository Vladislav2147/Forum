package com.example.forumdisigin.ui.topic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TopicViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is topic Fragment"

    }
    val text: LiveData<String> = _text
}