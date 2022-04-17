package com.example.infra.repository

import com.example.domain.model.Memo
import com.example.domain.repository.MemoRepository
import com.example.infra.source.local.AppDatabase
import com.example.infra.source.local.entity.MemoEntity
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : MemoRepository {
    override suspend fun getMemoList(): List<Memo> {
        return db.memoDao().getMemoList().map { it.toMemo() }
    }

    override suspend fun getMemo(memoId: String): Memo {
        // todo
        return Memo.createSampleObject()
    }

    override suspend fun createMemo(memo: Memo): Memo {
        db.memoDao().insertMemo(MemoEntity.fromMemo(memo))
        return memo
    }

    override suspend fun updateMemo(memo: Memo): Memo {
        // todo
        return Memo.createSampleObject()
    }

    override suspend fun deleteMemo(memo: Memo) {
        // todo
    }
}