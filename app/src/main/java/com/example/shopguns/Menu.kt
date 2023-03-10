package com.example.shopguns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Menu : AppCompatActivity() {
//перехід по кнопкам
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
    fun onClickList(view: View) {// список зброї
        val intent = Intent(this, ListGuns::class.java)
        startActivity(intent)
    }
    fun onClickSellList(view: View){//список проданої зброї
        val intent = Intent(this, SellGuns::class.java)
        startActivity(intent)
    }
    fun onClickAddGun(view: View){//список проданої зброї
        val intent = Intent(this, AddGuns::class.java)
        startActivity(intent)
    }
    fun onClickGunsSellery(view: View){//список проданої зброї
        val intent = Intent(this, GunsSellery::class.java)
        startActivity(intent)
    }
    fun onClickBackGuns(view: View) {//список поверненої зброї
        val intent = Intent(this, BackGuns::class.java)
        startActivity(intent)
    }

}