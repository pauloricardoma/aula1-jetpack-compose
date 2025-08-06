package com.example.aula1.data

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class Transaction(
    val uuid: String = UUID.randomUUID().toString(),
    val value: BigDecimal = BigDecimal.valueOf(0),
    val category: String = "",
    val date: Date = Date()
)