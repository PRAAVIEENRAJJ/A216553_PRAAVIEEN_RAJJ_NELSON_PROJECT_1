package com.example.a216553_praavieen_rajj_nelson_project_1.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.a216553_praavieen_rajj_nelson_project_1.data.UserProfile

@Composable
fun ProfileScreen(profile: UserProfile, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        TextButton(onClick = onBack) { Text("← Back") }
        Text("My Profile", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                ProfileRow("Full Name", profile.fullName)
                ProfileRow("Username", profile.username)
                ProfileRow("Email", profile.email)
                ProfileRow("Phone", profile.phone)
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}