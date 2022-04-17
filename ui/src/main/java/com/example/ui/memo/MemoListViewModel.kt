package com.example.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Memo
import com.example.domain.repository.MemoRepository
import com.example.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val _flow: MutableStateFlow<Resource<List<MemoUiModel>>> =
        MutableStateFlow(Resource.Empty)
    val flow: Flow<Resource<List<MemoUiModel>>> = _flow

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            _flow.value = Resource.Loading
            delay(500)
            try {
                val memoList = memoRepository.getMemoList()
                _flow.value = Resource.Success(memoList.map { MemoUiModel.fromMemo(it) })
            } catch (e: Exception) {
                _flow.value = Resource.Failure("読み込みに失敗しました")
            }
        }
    }

    fun createMemo() {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.createMemo(
                Memo(
                    id = UUID.randomUUID().toString(),
                    title = "タイトル",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now()
                )
            )
            load()
        }
    }

    fun deleteMemo(memoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.deleteMemo(
                memoId
            )
            load()
        }
    }
}
