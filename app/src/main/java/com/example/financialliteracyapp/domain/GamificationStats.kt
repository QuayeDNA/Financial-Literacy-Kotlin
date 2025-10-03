package com.example.financialliteracyapp.domain

import com.google.firebase.Timestamp

data class GamificationStats(
    val xp: Int,
    val level: Int,
    val coins: Int,
    val badges: List<String>, // badge IDs
    val currentStreak: Int,
    val longestStreak: Int,
    val lastLoginDate: Timestamp,
    val totalLessonsCompleted: Int,
    val totalExpensesLogged: Int
)