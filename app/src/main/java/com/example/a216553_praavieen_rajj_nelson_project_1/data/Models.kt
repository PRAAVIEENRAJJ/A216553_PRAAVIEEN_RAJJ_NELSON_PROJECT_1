package com.example.a216553_praavieen_rajj_nelson_project_1.data

data class UserProfile(
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val phone: String = ""
)

data class Envelope(
    val title: String,
    val emoji: String,
    val budgetTotal: Float,
    val budgetSpent: Float
)