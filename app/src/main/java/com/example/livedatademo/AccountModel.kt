package com.example.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountModel(accNo:String="", owner:String="", balance:Double=0.0):ViewModel() {

    private var _accNo : String
        var accountNo : String
        get() = _accNo
        set(value){_accNo = value}

    private var _owner : String
        var owner : String
        get() = _owner
        set(value) {_owner = value}

    private var _balance= MutableLiveData<Double>()
        var balance : MutableLiveData<Double>
        get() = _balance
        set(amount){_balance.value = amount.value}


    init {
        this._accNo = accNo
        this._owner = owner
        this.balance.value = balance
    }

    fun deposit(amount: Double) {
        _balance.value = _balance.value?.plus(amount)
    }

    fun withdraw(amount:Double){
        _balance.value = _balance.value?.minus(amount)
    }


}