package com.genrikhsalexandr.filterfeature.presentation

import androidx.lifecycle.ViewModel
import com.genrikhsalexandr.filterfeature.domain.FilterButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilterViewModel : ViewModel() {
    private val _selectedButton = MutableStateFlow<FilterButton>(FilterButton.None)
    val selectedButton: StateFlow<FilterButton> = _selectedButton

    fun onButtonClicked(button: FilterButton) {
        _selectedButton.value = if (_selectedButton.value == button) {
            FilterButton.None
        } else {
            button
        }
    }
}