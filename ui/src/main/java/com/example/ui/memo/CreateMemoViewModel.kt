package com.example.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Memo
import com.example.domain.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateMemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<CreateMemoUiState> =
        MutableStateFlow(CreateMemoUiState(""))
    val stateFlow: StateFlow<CreateMemoUiState> = _stateFlow

    // 同じ値を連続して受け取るため、SharedFlow を使用する
    private val _effectFlow: MutableSharedFlow<CreateMemoSideEffect> =
        MutableSharedFlow()
    val effectFlow: SharedFlow<CreateMemoSideEffect> = _effectFlow

    fun createMemo() {
        val title = stateFlow.value.title

        viewModelScope.launch(Dispatchers.IO) {

            if (title.isEmpty()) {
                _effectFlow.emit(CreateMemoSideEffect.CreateFailure("メモの内容を入力してください"))
                return@launch
            }

            memoRepository.createMemo(
                Memo(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )
            _effectFlow.emit(CreateMemoSideEffect.CreateSuccess)
        }
    }

    fun onTitleChanged(newValue: String) {
        _stateFlow.value = CreateMemoUiState(title = newValue)
    }

}

data class CreateMemoUiState(
    val title: String,
)

sealed class CreateMemoSideEffect {
    object Empty : CreateMemoSideEffect()
    object CreateSuccess : CreateMemoSideEffect()
    data class CreateFailure(val message: String) : CreateMemoSideEffect()
}
