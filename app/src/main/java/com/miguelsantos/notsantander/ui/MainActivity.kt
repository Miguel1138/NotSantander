package com.miguelsantos.notsantander.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miguelsantos.notsantander.R
import com.miguelsantos.notsantander.data.Account
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
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
        main_tv_username.text = resources.getString(R.string.hello, clientName[0])

        main_tv_agency.text = "Ag ${account.agency}"
        main_tv_account_num.text = "Cc ${account.accountNumber}"
        main_tv_available_balance.text = "R$ ${account.balance}"
        main_tv_balance_plus_limit.text = "R$ ${account.limit}"

        //Pegando os 4 últimos números do cartão
        val card = account.card.cardNumber
        main_tv_card_final_nums.text =
            card.substring(card.length - 5)
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