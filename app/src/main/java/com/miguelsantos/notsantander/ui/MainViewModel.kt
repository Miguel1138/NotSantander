package com.miguelsantos.notsantander.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguelsantos.notsantander.data.Account
import com.miguelsantos.notsantander.data.local.FakeData

class MainViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<Account> = MutableLiveData()

    fun searchClientAccount(): LiveData<Account> {
        mutableLiveData.value = FakeData().getLocalData()

        return mutableLiveData
    }

}