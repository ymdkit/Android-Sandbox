package com.example.infra.repository

import com.example.domain.model.Memo
import com.example.domain.repository.MemoRepository
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor() : MemoRepository {
    override suspend fun getMemoList(): List<Memo> {
        // todo
        return List(10) { i -> Memo(id = "$i", "タイトル：$i") }
    }

    override suspend fun getMemo(memoId: String): Memo {
        // todo
        return Memo.createSampleObject()
    }

    override suspend fun createMemo(memo: Memo): Memo {
        // todo
        return Memo.createSampleObject()
    }

    override suspend fun updateMemo(memo: Memo): Memo {
        // todo
        return Memo.createSampleObject()
    }

    override suspend fun deleteMemo(memo: Memo) {
        // todo
    }
}