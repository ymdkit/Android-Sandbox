package com.example.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor() : ViewModel() {

    private val _flow: MutableStateFlow<Resource<List<MemoUiModel>>> =
        MutableStateFlow(Resource.Empty)
    val flow: Flow<Resource<List<MemoUiModel>>> = _flow

    fun load() {
        viewModelScope.launch {
            _flow.value = Resource.Loading
            delay(500)
            _flow.value = Resource.Success(
                List(10) { i -> MemoUiModel(id = "$i", "タイトル：$i") }
            )
        }
    }
}
