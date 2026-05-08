package com.example.a216553_praavieen_rajj_nelson_project_1.Screens

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a216553_praavieen_rajj_nelson_project_1.data.Envelope
import com.example.a216553_praavieen_rajj_nelson_project_1.data.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    profile: UserProfile,
    envelopeList: List<Envelope>,
    onNavigateToAdd: () -> Unit,
    onViewProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val displayName = if (profile.fullName.isNotEmpty()) profile.fullName else if (profile.username.isNotEmpty()) profile.username else "User"

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd, containerColor = MaterialTheme.colorScheme.primaryContainer) {
                Icon(Icons.Default.Add, contentDescription = "Add Envelope")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp).verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("A216553 | Smart Budget", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline) // Required Matric ID[cite: 1]
                Button(onClick = onLogout) { Text("Logout") }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary), shape = RoundedCornerShape(20.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Hello, $displayName 👋", color = MaterialTheme.colorScheme.onPrimary)
                    Text("RM 850.00", style = MaterialTheme.typography.displayMedium, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
                    Text("Available Balance", color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedButton(onClick = onViewProfile, modifier = Modifier.fillMaxWidth()) { Text("View My Profile →") }
            Spacer(modifier = Modifier.height(20.dp))
            Text("Budget Envelopes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            envelopeList.forEach { envelope ->
                ExpandableEnvelopeCard(envelope.title, envelope.emoji, envelope.budgetTotal, envelope.budgetSpent)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ExpandableEnvelopeCard(title: String, emoji: String, budgetTotal: Float, budgetSpent: Float) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier = Modifier.fillMaxWidth().animateContentSize().clickable { expanded = !expanded }, shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Text(emoji, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text("RM %.0f / RM %.0f".format(budgetSpent, budgetTotal), style = MaterialTheme.typography.bodySmall)
                    }
                }
                Icon(if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null)
            }
            if (expanded) {
                LinearProgressIndicator(progress = { (budgetSpent/budgetTotal) }, modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
            }
        }
    }
}