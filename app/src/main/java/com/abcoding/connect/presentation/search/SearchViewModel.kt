package com.abcoding.connect.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.abcoding.connect.presentation.util.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {

    private val _searchState = mutableStateOf(StandardTextFieldState())
    val searchState: State<StandardTextFieldState> = _searchState

    fun setSearchState(state: StandardTextFieldState) {
        _searchState.value = state
    }
}