package com.example.infra.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.infra.source.local.entity.MemoEntity

@Dao
interface MemoDao {

    @Query("SELECT * FROM memos")
    suspend fun getMemoList(): List<MemoEntity>

    @Insert
    suspend fun insertMemo(memoEntity: MemoEntity)

    @Query("DELETE FROM memos WHERE id = :memoId")
    fun delete(memoId: String)
}