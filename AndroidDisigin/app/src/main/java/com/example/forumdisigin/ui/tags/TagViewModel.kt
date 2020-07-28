package com.example.forumdisigin.ui.tags

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TagViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tags Fragment"
    }
    val text: LiveData<String> = _text
}