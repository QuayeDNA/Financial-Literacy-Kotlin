package com.example.financialliteracyapp.data.repository

import com.example.financialliteracyapp.data.remote.FirebaseAuthService
import com.example.financialliteracyapp.data.remote.FirestoreService
import com.example.financialliteracyapp.domain.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val firestoreService: FirestoreService
) {

    val currentUser: FirebaseUser?
        get() = firebaseAuthService.currentUser

    fun signInWithEmailAndPassword(email: String, password: String): Flow<Result<FirebaseUser>> = flow {
        try {
            val result = firebaseAuthService.signInWithEmailAndPassword(email, password)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String): Flow<Result<FirebaseUser>> = flow {
        try {
            val result = firebaseAuthService.createUserWithEmailAndPassword(email, password)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun saveUserProfile(user: User): Flow<Result<Unit>> = flow {
        try {
            val result = firestoreService.saveUser(user)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getUserProfile(userId: String): Flow<Result<User?>> = flow {
        try {
            val result = firestoreService.getUser(userId)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun signOut() {
        firebaseAuthService.signOut()
    }
}