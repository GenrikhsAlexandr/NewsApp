package com.genrikhsalexandr.filterfeature.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.filterfeature.domain.FilterButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    private val _selectedButton = MutableStateFlow<FilterButton>(FilterButton.None)
    val selectedButton: StateFlow<FilterButton> = _selectedButton

    fun onButtonClicked(button: FilterButton) {
        _selectedButton.value = if (_selectedButton.value == button) {
            FilterButton.None
        } else {
            button
        }
    }

    fun onNavigationBackFilter(fragment: FragmentManager) {
        navigator.navigateBackFilter(fragment)
    }

    fun onNavigationSavedFilter(fragment: FragmentManager) {
        navigator.navigateSavedFilter(fragment)
    }
}