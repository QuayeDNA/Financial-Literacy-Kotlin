package com.example.financialliteracyapp.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSetupScreen(
    onProfileComplete: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var displayName by remember { mutableStateOf("") }
    var financialExperienceLevel by remember { mutableStateOf("") }
    var ageRange by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("USD") }
    var acceptedTerms by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            onProfileComplete()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Complete Your Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = displayName,
            onValueChange = { displayName = it },
            label = { Text("Display Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Financial Experience Level Dropdown
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = financialExperienceLevel,
                onValueChange = {},
                label = { Text("Financial Experience Level") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf("Beginner", "Intermediate", "Advanced").forEach { level ->
                    DropdownMenuItem(
                        text = { Text(level) },
                        onClick = {
                            financialExperienceLevel = level
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Age Range Dropdown
        var ageExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = ageExpanded,
            onExpandedChange = { ageExpanded = it }
        ) {
            OutlinedTextField(
                value = ageRange,
                onValueChange = {},
                label = { Text("Age Range") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = ageExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = ageExpanded,
                onDismissRequest = { ageExpanded = false }
            ) {
                listOf("16-25", "26-35", "36-45", "46+").forEach { range ->
                    DropdownMenuItem(
                        text = { Text(range) },
                        onClick = {
                            ageRange = range
                            ageExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Currency Dropdown
        var currencyExpanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = currencyExpanded,
            onExpandedChange = { currencyExpanded = it }
        ) {
            OutlinedTextField(
                value = currency,
                onValueChange = {},
                label = { Text("Preferred Currency") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = currencyExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = currencyExpanded,
                onDismissRequest = { currencyExpanded = false }
            ) {
                listOf("USD", "EUR", "GBP", "GHS").forEach { curr ->
                    DropdownMenuItem(
                        text = { Text(curr) },
                        onClick = {
                            currency = curr
                            currencyExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and Privacy Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = acceptedTerms,
                onCheckedChange = { acceptedTerms = it }
            )
            Text(
                text = "I accept the Terms of Service and Privacy Policy",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (displayName.isNotBlank() && financialExperienceLevel.isNotBlank() &&
                    ageRange.isNotBlank() && acceptedTerms) {
                    viewModel.saveUserProfile(displayName, financialExperienceLevel, ageRange, currency)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = displayName.isNotBlank() && financialExperienceLevel.isNotBlank() &&
                     ageRange.isNotBlank() && acceptedTerms && authState !is AuthState.Loading
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text("Complete Profile")
            }
        }

        if (authState is AuthState.Error) {
            Text(
                text = (authState as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}