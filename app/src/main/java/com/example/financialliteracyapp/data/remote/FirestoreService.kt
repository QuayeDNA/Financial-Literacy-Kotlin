package com.example.financialliteracyapp.data.remote

import com.example.financialliteracyapp.domain.Transaction
import com.example.financialliteracyapp.domain.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreService @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    private fun getUserDocument(userId: String) = firestore.collection("users").document(userId)

    private fun getUserTransactionsCollection(userId: String) = getUserDocument(userId).collection("transactions")

    suspend fun saveUser(user: User): Result<Unit> {
        return try {
            getUserDocument(user.userId).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUser(userId: String): Result<User?> {
        return try {
            val document = getUserDocument(userId).get().await()
            val user = document.toObject(User::class.java)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveTransaction(userId: String, transaction: Transaction): Result<Unit> {
        return try {
            getUserTransactionsCollection(userId).document(transaction.transactionId).set(transaction).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getUserTransactions(userId: String): Flow<List<Transaction>> = callbackFlow {
        val listener = getUserTransactionsCollection(userId)
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val transactions = snapshot?.documents?.mapNotNull { it.toObject(Transaction::class.java) } ?: emptyList()
                trySend(transactions)
            }

        awaitClose { listener.remove() }
    }

    // Add more methods as needed
}