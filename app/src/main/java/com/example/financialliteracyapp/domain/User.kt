package com.example.financialliteracyapp.domain

import com.google.firebase.Timestamp

data class User(
    val userId: String,
    val email: String,
    val displayName: String?,
    val avatarUrl: String?,
    val bio: String?,
    val createdAt: Timestamp,
    val financialExperienceLevel: String, // Beginner|Intermediate|Advanced
    val ageRange: String, // 16-25|26-35|36-45
    val currency: String, // USD|EUR|GBP|GHS
    val privacySettings: PrivacySettings
)

data class PrivacySettings(
    val profileVisibility: Boolean,
    val leaderboardParticipation: Boolean
)