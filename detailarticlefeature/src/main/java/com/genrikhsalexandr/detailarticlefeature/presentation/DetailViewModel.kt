package com.genrikhsalexandr.detailarticlefeature.presentation

import androidx.lifecycle.ViewModel
import com.genrikhsalexandr.detailarticlefeature.domain.DetailInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DetailViewModel  @Inject constructor(
    private val interactor: DetailInteractor,
    ) : ViewModel(){

     val isIconClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onFavoriteButtonClicked() {
        isIconClick.value = !isIconClick.value
    }
}