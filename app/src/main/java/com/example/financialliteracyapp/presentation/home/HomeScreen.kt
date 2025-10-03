package com.example.financialliteracyapp.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    userName: String = "User",
    xp: Int = 0,
    level: Int = 1,
    onLearnClick: () -> Unit,
    onBudgetClick: () -> Unit,
    onCommunityClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Hello, $userName!",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Level $level • $xp XP",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Quick Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onLearnClick) {
                Text("Learn")
            }
            Button(onClick = onBudgetClick) {
                Text("Budget")
            }
            Button(onClick = onCommunityClick) {
                Text("Community")
            }
            Button(onClick = onProfileClick) {
                Text("Profile")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Daily Challenge
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Daily Challenge",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Complete a lesson to earn bonus XP!")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onLearnClick) {
                    Text("Start Learning")
                }
            }
        }
    }
}