package com.example.shopguns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.shopguns.databinding.ActivityGunsSelleryBinding

class GunsSellery : AppCompatActivity() {
    lateinit var bindingClass: ActivityGunsSelleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityGunsSelleryBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
    fun onClicComplite(view: View){

    }
}