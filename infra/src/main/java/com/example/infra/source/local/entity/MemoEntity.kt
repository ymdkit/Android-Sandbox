package com.example.infra.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Memo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "memos")
data class MemoEntity(
    @PrimaryKey val id: String,
    val title: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String
) {
    companion object {
        fun fromMemo(memo: Memo) = MemoEntity(
            id = memo.id,
            title = memo.title,
            createdAt = memo.createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            updatedAt = memo.updatedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        )
    }

    fun toMemo() = Memo(
        id = id,
        title = title,
        createdAt = LocalDateTime.parse(createdAt),
        updatedAt = LocalDateTime.parse(updatedAt),
    )
}