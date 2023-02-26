package com.example.shopguns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun onClickList(view: View) {
        val intent = Intent(this, ListGuns::class.java)
        startActivity(intent)
    }
    fun onClickSellList(view: View){
        val intent = Intent(this, SellGuns::class.java)
        startActivity(intent)
    }

}