package com.example.aula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aula1.ui.theme.Aula1Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun add(transaction: String) {
        val transactions = _uiState.value.transactions.toMutableList()
        transactions.add(transaction)
        _uiState.value = UiState(transactions)
    }

    data class UiState(
        val transactions: List<String> = emptyList()
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Aula1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Welcome(
                            modifier = Modifier.padding(innerPadding)
                        )
                        Transactions()
                    }
                }
            }
        }
    }
}

@Composable
fun Transactions(viewModel: MyViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(uiState.transactions.size) { index ->
                Transaction(uiState.transactions[index])
            }
        }
        Button(
            onClick = {
                viewModel.add("New Transaction")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Add new Transaction"
            )
        }
    }
}

@Composable
fun Transaction(transaction: String) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = transaction,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun Welcome(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Welcome back, \n Paulo Ricardo!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Clear Transactions",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Aula1Theme {
        Column {
            Welcome()
            Transactions()
        }
    }
}
