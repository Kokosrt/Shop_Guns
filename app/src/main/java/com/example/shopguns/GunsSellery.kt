package com.example.shopguns

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.shopguns.databinding.ActivityGunsSelleryBinding
import com.example.shopguns.models.Gun
import com.example.shopguns.services.GunDatabaseHelper

class GunsSellery : AppCompatActivity() {
    lateinit var bindingClass: ActivityGunsSelleryBinding
    private lateinit var spinner: Spinner
    private lateinit var dbHelper: GunDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityGunsSelleryBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        dbHelper = GunDatabaseHelper(this)
        val gunsList = dbHelper.getGunsByAvailability(true)

        spinner = bindingClass.spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gunsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedGun = parent?.getItemAtPosition(position) as Gun
                bindingClass.tPriceGun.text = selectedGun.price.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    fun onClickComplete(view: View) {
        val selectedGun = spinner.selectedItem as Gun
        dbHelper.updateGunAvailability(selectedGun.id, false)
        finish()
    }
}