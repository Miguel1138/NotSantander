package com.miguelsantos.notsantander.data

data class Account(
    val accountNumber: String,
    val agency: String,
    val balance: String,
    val limit: String,
    val client: Client,
    val card: CreditCard
)