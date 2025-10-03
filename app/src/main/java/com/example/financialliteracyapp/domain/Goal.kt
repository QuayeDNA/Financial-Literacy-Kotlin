package com.example.financialliteracyapp.domain

import com.google.firebase.Timestamp

data class Goal(
    val goalId: String,
    val title: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val category: String,
    val deadline: Timestamp,
    val createdAt: Timestamp,
    val status: String, // active|completed|abandoned
    val completedAt: Timestamp?,
    val contributions: List<Contribution>
)

data class Contribution(
    val amount: Double,
    val date: Timestamp,
    val description: String?
)