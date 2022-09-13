package com.example.livedatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatademo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var accountModel: AccountModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        //accountModel = AccountModel("A1233","Junyi",2.0)
        accountModel = ViewModelProvider(this).get(AccountModel::class.java)

        if (accountModel.owner==""){
            accountModel.owner="Jun"
            accountModel.accountNo="A149"
            accountModel.balance.value=150.0
        }

        display()

        accountModel.balance.observe(this, Observer {
            newBalance -> binding.tvBalance.text = newBalance.toString()

            //use that given it
            //binding.tvBalance.text = it.toString()
        })

        binding.btnDeposit.setOnClickListener(){
            val amount:Double = binding.tfAmount.text.toString().toDouble()
            accountModel.deposit(amount)
            display()
        }
        binding.btnWitdraw.setOnClickListener(){
            val amount:Double = binding.tfAmount.text.toString().toDouble()
            accountModel.withdraw(amount)
            display()
        }
    }
    fun display(){
        binding.tvName.text = accountModel.owner.toString()
        binding.tvAccNo.text = accountModel.accountNo
        binding.tvBalance.text = accountModel.balance.value.toString()
    }

}