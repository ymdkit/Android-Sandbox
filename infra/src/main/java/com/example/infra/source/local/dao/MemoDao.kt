package com.example.infra.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.infra.source.local.entity.MemoEntity

@Dao
interface MemoDao {

    @Query("SELECT * FROM memos")
    suspend fun getMemoList(): List<MemoEntity>

}