package com.genrikhsalexandr.souresfeature.presentation.sources

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.souresfeature.domain.SourcesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
    private val navigator: Navigator,
    private val interactor: SourcesInteractor
) : ViewModel() {

    private val _source: MutableStateFlow<List<Source>> = MutableStateFlow(emptyList())

    val source: StateFlow<List<SourcesItemList>> = _source.map { news ->
        news.map {
            SourcesItemList(
                category = it.category,
                name = it.sourceName,
                country = it.country,
                source = it
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope
        viewModelScope.launch {
            try {
                _source.value = interactor.getSourcesList() ?: emptyList()
                println("source= ${_source.value}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onNewsItemClick(source: Source, fragmentManager: FragmentManager) {
        navigator.navigateToArticlesSource(source, fragmentManager)
    }
}