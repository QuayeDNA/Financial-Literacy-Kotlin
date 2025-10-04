package com.example.financialliteracyapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialliteracyapp.data.repository.AuthRepository
import com.example.financialliteracyapp.domain.User
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        authRepository.currentUser?.let { firebaseUser ->
            viewModelScope.launch {
                authRepository.getUserProfile(firebaseUser.uid).collect { result ->
                    result.onSuccess { user ->
                        _currentUser.value = user
                        _authState.value = if (user != null) AuthState.Authenticated else AuthState.NeedsProfile
                    }.onFailure {
                        _authState.value = AuthState.Error("Failed to load user profile")
                    }
                }
            }
        } ?: run {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun signIn(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            authRepository.signInWithEmailAndPassword(email, password).collect { result ->
                result.onSuccess { firebaseUser ->
                    // Load user profile after successful sign in
                    authRepository.getUserProfile(firebaseUser.uid).collect { profileResult ->
                        profileResult.onSuccess { user ->
                            _currentUser.value = user
                            _authState.value = if (user != null) AuthState.Authenticated else AuthState.NeedsProfile
                        }.onFailure {
                            _authState.value = AuthState.Error("Failed to load user profile")
                        }
                    }
                }.onFailure { exception ->
                    _authState.value = AuthState.Error(exception.localizedMessage ?: "Sign in failed")
                }
            }
        }
    }

    fun signUp(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            authRepository.createUserWithEmailAndPassword(email, password).collect { result ->
                result.onSuccess { firebaseUser ->
                    _authState.value = AuthState.NeedsProfile
                }.onFailure { exception ->
                    _authState.value = AuthState.Error(exception.localizedMessage ?: "Sign up failed")
                }
            }
        }
    }

    fun saveUserProfile(
        displayName: String,
        financialExperienceLevel: String,
        ageRange: String,
        currency: String
    ) {
        val firebaseUser = authRepository.currentUser ?: return

        val user = User(
            userId = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            displayName = displayName,
            avatarUrl = null,
            bio = null,
            createdAt = Timestamp.now(),
            financialExperienceLevel = financialExperienceLevel,
            ageRange = ageRange,
            currency = currency,
            privacySettings = com.example.financialliteracyapp.domain.PrivacySettings(
                profileVisibility = true,
                leaderboardParticipation = true
            )
        )

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            authRepository.saveUserProfile(user).collect { result ->
                result.onSuccess {
                    _currentUser.value = user
                    _authState.value = AuthState.Authenticated
                }.onFailure { exception ->
                    _authState.value = AuthState.Error(exception.localizedMessage ?: "Failed to save profile")
                }
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _currentUser.value = null
        _authState.value = AuthState.Unauthenticated
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    object NeedsProfile : AuthState()
    object Authenticated : AuthState()
    data class Error(val message: String) : AuthState()
}