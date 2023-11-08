package com.genrikhsalexandr.detailarticlefeature.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel : ViewModel() {

     val isIconClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onFavoriteButtonClicked() {
        isIconClick.value = !isIconClick.value
    }
}