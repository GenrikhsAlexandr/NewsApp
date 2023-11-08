package com.genrikhsalexandr.detailarticlefeature.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel:ViewModel() {

    private val _isIconClick: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isIconClick = _isIconClick
}