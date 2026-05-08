package com.example.a216553_praavieen_rajj_nelson_project_1.ViewModel

import androidx.lifecycle.ViewModel
import com.example.a216553_praavieen_rajj_nelson_project_1.data.Envelope
import com.example.a216553_praavieen_rajj_nelson_project_1.data.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserProfile())
    val uiState: StateFlow<UserProfile> = _uiState.asStateFlow()

    // Shared list for project requirements
    private val _envelopes = MutableStateFlow(
        listOf(
            Envelope("Food", "🍔", 300f, 120f),
            Envelope("Transport", "🚗", 200f, 80f)
        )
    )
    val envelopes: StateFlow<List<Envelope>> = _envelopes.asStateFlow()

    fun updateFromLogin(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun updateFromRegister(fullName: String, username: String, email: String, phone: String) {
        _uiState.update { UserProfile(fullName, username, email, phone) }
    }

    // Logical function to add an item via UI
    fun addEnvelope(title: String, budget: Float) {
        val newEnv = Envelope(title, "💰", budget, 0f)
        _envelopes.update { it + newEnv }
    }

    fun clear() {
        _uiState.value = UserProfile()
        _envelopes.value = emptyList()
    }
}