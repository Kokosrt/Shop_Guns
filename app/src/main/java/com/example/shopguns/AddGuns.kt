package com.example.shopguns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.shopguns.databinding.ActivityAddGunsBinding
import com.example.shopguns.models.Gun
import com.example.shopguns.services.GunDatabaseHelper

class AddGuns : AppCompatActivity() {
    lateinit var bindingClass: ActivityAddGunsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityAddGunsBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickAddGuns(view: View){ //додаєм зброю
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)

        val uniqueCode = bindingClass.tUniqueCode.text.toString().toInt()
        val nameGuns = bindingClass.tNameGuns.text.toString()
        val categories = bindingClass.tCategories.text.toString()
        val price = bindingClass.tPrice.text.toString().toInt()

        val newGun = Gun(uniqueCode, nameGuns, categories, price,true)

        val gunDatabaseHelper = GunDatabaseHelper(this)
        if (gunDatabaseHelper.isCodeGoodsUnique(uniqueCode)) {
            gunDatabaseHelper.addNewGun(newGun)

            finish()

        } else {
            Toast.makeText(this, "Унікальний код вже використано!", Toast.LENGTH_SHORT).show()
        }
    }
}