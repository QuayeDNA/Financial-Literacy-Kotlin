package com.example.financialliteracyapp.domain

import com.google.firebase.Timestamp

data class UserProgress(
    val lessonId: String,
    val completed: Boolean,
    val completedAt: Timestamp?,
    val quizScore: Int?,
    val bookmarked: Boolean
)