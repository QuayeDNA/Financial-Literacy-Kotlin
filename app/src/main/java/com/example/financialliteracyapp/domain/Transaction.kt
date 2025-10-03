package com.example.financialliteracyapp.domain

import com.google.firebase.Timestamp

data class Transaction(
    val transactionId: String,
    val type: String, // expense|income
    val amount: Double,
    val category: String,
    val description: String,
    val date: Timestamp,
    val receiptUrl: String?,
    val createdAt: Timestamp
)