package com.example.domain.model

import java.time.LocalDateTime

data class Memo(
    val id: String,
    val title: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun createSampleObject() = Memo(
            id = "1",
            title = "サンプル",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
    }
}