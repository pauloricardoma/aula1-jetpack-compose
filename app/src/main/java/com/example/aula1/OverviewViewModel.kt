package com.example.aula1

import androidx.lifecycle.ViewModel
import com.example.aula1.data.DummyRepository
import com.example.aula1.data.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

class OverviewViewModel(
    private val repository: DummyRepository = DummyRepository
): ViewModel() {
    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun addTransaction(transaction: Transaction) {
        repository.add(transaction)
        updateUiState()
    }

    fun clearTransactions() {
        repository.clear()
        updateUiState()
    }

    fun updateTransaction(transaction: Transaction) {
        repository.update(transaction)
        updateUiState()
    }

    fun deleteTransaction(uuid: String) {
        repository.delete(uuid)
        updateUiState()
    }

    fun findTransaction(uuid: String) = repository.find(uuid)

    fun filterTransaction(category: String) {
        filter.value = category
        updateUiState()
    }

    fun clearFilter() {
        filter.value = null
        updateUiState()
    }

    private fun updateUiState() {
        val transactionListSaved = repository.transactions
        val transactions = if (filter.value != null) {
            transactionListSaved.filter { it.category == filter.value }
        } else {
            transactionListSaved
        }
        _uiState.value = UiState(
            transactions = transactions,
            total = transactionListSaved.sumOf { it.value }
        )
    }

    data class UiState(
        val transactions: List<Transaction> = emptyList(),
        val total: BigDecimal = BigDecimal.valueOf(0),
    )
}