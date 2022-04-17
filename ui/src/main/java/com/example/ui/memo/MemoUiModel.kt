package com.example.ui.memo

import com.example.domain.model.Memo

data class MemoUiModel(
    val id: String,
    val title: String
) {
    companion object {
        fun fromMemo(memo: Memo): MemoUiModel = MemoUiModel(
            id = memo.id,
            title = memo.title
        )
    }
}
