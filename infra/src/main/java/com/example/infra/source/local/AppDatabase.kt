package com.example.infra.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.infra.source.local.dao.MemoDao
import com.example.infra.source.local.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}