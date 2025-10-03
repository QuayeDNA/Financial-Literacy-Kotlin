package com.example.financialliteracyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val transactionId: String,
    val type: String, // expense|income
    val amount: Double,
    val category: String,
    val description: String,
    val date: Long, // timestamp millis
    val receiptUrl: String?,
    val createdAt: Long
)