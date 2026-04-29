package com.dorothy.myappwork.ui.screens.home

import androidx.lifecycle.ViewModel
import com.dorothy.myappwork.data.models.HobbyDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel : ViewModel() {

    //    state (backing property +actual state
    private val _hobby = MutableStateFlow<HobbyDataModel>(HobbyDataModel())
    val hobby = _hobby.asStateFlow()

    //    "CRUD" - methods
    fun createHobby(name: String, description: String) {
        _hobby.value = HobbyDataModel(name = name, description = description)
    }

}

