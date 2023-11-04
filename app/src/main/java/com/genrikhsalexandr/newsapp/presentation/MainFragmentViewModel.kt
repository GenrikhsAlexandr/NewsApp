package com.genrikhsalexandr.newsapp.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    val navigator: Navigator
) : ViewModel() {

    private val _isAppBarVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isAppBarVisible = _isAppBarVisible

    private val navigatorListener: Navigator.Listener = Navigator.Listener { destination: Screen ->
        when (destination) {
            Screen.HEADLINES -> {
                isAppBarVisible.value = true
            }

            Screen.FAVORITES -> {
                isAppBarVisible.value = true
            }

            Screen.SOURCES -> {
                isAppBarVisible.value = true
            }

            Screen.DETAIL_ARTICLE -> {
                isAppBarVisible.value = false
            }

            Screen.SEARCH -> {
                isAppBarVisible.value = false
            }

            Screen.FILTER -> {
                isAppBarVisible.value = false
            }
        }
    }

    init {
        navigator.listener = navigatorListener
    }
}
