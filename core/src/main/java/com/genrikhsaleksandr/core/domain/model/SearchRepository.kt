package com.genrikhsaleksandr.core.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SearchRepository {
    private val _searchRequest: MutableStateFlow <String> = MutableStateFlow("")
    val searchRequest:StateFlow<String> = _searchRequest

    fun setSearchRequest(request: String){
        _searchRequest.value = request
        println("setSearchRequest: $request")
    }
}