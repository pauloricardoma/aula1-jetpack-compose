package com.example.aula1

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import com.example.aula1.data.Transaction
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("MMM. dd", Locale.getDefault())
    return dateFormat.format(this)
}

fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return  numberFormat.format(this)
}

val categories = listOf(
    "Food" to Icons.Default.Face,
    "Transport" to Icons.Default.Face,
    "Shopping" to Icons.Default.Face,
    "Health" to Icons.Default.Face,
    "Entertainment" to Icons.Default.Face,
    "Utilities" to Icons.Default.Face,
)

fun randomTRansaction() = Transaction(
    category = categories.shuffled().first().first,
    value = BigDecimal.valueOf(Random.nextDouble() / Random.nextDouble())
)