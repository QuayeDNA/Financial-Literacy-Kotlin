package com.example.financialliteracyapp.presentation.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    onComplete: () -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }

    val pages = listOf(
        "Welcome to Financial Literacy App!",
        "Learn budgeting, saving, and investing through gamification.",
        "Track your expenses and set financial goals.",
        "Earn XP, unlock badges, and compete with friends!"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pages[currentPage],
            style = MaterialTheme.typography.headlineSmall,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (currentPage > 0) {
                Button(onClick = { currentPage-- }) {
                    Text("Previous")
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            if (currentPage < pages.size - 1) {
                Button(onClick = { currentPage++ }) {
                    Text("Next")
                }
            } else {
                Button(onClick = onComplete) {
                    Text("Get Started")
                }
            }
        }
    }
}