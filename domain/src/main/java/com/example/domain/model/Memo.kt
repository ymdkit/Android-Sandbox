package com.example.domain.model

data class Memo(
    val id: String,
    val title: String
) {
    companion object {
        fun createSampleObject() = Memo(id = "1", title = "サンプル")
    }
}