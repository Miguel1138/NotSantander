package com.miguelsantos.notsantander.data

data class Client(
    val name: String,
    var age: Int,
    val motherName: String,
    val address: String,
    var zipCode: String,
    var hasCredit: Boolean
)
