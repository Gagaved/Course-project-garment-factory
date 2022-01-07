package ru.fefu.courseproject_garmentfactory.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Александр\nАлександрович\nАлександров"
    }
    val text: LiveData<String> = _text
}