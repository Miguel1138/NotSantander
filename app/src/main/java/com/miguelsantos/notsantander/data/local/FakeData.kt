package com.miguelsantos.notsantander.data.local

import com.miguelsantos.notsantander.data.Account
import com.miguelsantos.notsantander.data.Client
import com.miguelsantos.notsantander.data.CreditCard

class FakeData {

    fun getLocalData(): Account {
        val client = Client(
            "Juracy Ademar de Farias",
            45,
            "Jurema Ademar de Farias",
            "Vila Sésamo, 777",
            "77775-777",
            true
        )

        val creditCard = CreditCard(
            "8888 7777 6666 4444",
            "Santander",
            client.name.toUpperCase()
        )

        return Account(
            "444557-4",
            "2543",
            "850,46",
            "1080,46",
            client,
            creditCard
        )
    }

}
