package com.genrikhsalexandr.filterfeature.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class FilterViewModel : ViewModel() {

    val isButtonPopularClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isButtonNewClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isButtonRelevantClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onButtonClicked() {
        isButtonNewClick.value = !isButtonNewClick.value
    }
}