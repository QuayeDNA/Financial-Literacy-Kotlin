package com.example.financialliteracyapp.domain

data class Lesson(
    val lessonId: String,
    val title: String,
    val category: String,
    val content: String, // markdown
    val imageUrl: String?,
    val videoUrl: String?,
    val order: Int,
    val quiz: Quiz,
    val xpReward: Int,
    val estimatedMinutes: Int
)

data class Quiz(
    val questions: List<QuizQuestion>
)

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)