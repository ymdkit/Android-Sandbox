package com.example.domain.repository

import com.example.domain.model.Memo

interface MemoRepository {
    suspend fun getMemoList(): List<Memo>
    suspend fun getMemo(memoId: String): Memo
    suspend fun createMemo(memo: Memo): Memo
    suspend fun updateMemo(memo: Memo): Memo
    suspend fun deleteMemo(memoId: String)
}