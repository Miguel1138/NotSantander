package com.miguelsantos.notsantander.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguelsantos.notsantander.R
import com.miguelsantos.notsantander.data.Account

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_toolbar))
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.reorder_white_24dp)
            // TODO: 27/05/2021 Procurar enventos de click no homeasup
        }
        findClient()
    }

    private fun findClient() {
        // Recuperar e instanciar o viewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.searchClientAccount().observe(this, Observer { result ->
            onBindView(result)
        })
    }

    private fun onBindView(account: Account) {
        // Pega o nome completo e divide pelos espaços.
        val clientName = account.client.name.split(" ")
        findViewById<TextView>(R.id.main_tv_username).text =
            String.format(resources.getString(R.string.hello), clientName[0])

        findViewById<TextView>(R.id.main_tv_agency).text =
            String.format(resources.getString(R.string.agency), account.agency)

        findViewById<TextView>(R.id.main_tv_account_num).text =
            String.format(resources.getString(R.string.account), account.accountNumber)

        findViewById<TextView>(R.id.main_tv_available_balance).text =
            String.format(resources.getString(R.string.available_balance_cash), account.balance)

        findViewById<TextView>(R.id.main_tv_balance_plus_limit).text =
            String.format(resources.getString(R.string.balance_limit_cash), account.limit)

        //Pegando os 4 últimos números do cartão
        val card = account.card.cardNumber
        findViewById<TextView>(R.id.main_tv_card_final_nums).text = String.format(
            resources.getString(R.string.final_card_numbers), card.substring(card.length - 5)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_notification -> {

                Toast.makeText(this, "Olha a notificação dindin", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}