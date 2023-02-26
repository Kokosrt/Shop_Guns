package com.example.shopguns


import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shopguns.services.GunDatabaseHelper

class SellGuns : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_guns)

        val dbHelper = GunDatabaseHelper(this)
        val gunsList = dbHelper.getGunsByAvailability(false)

        for (i in gunsList.indices) {
            val row = TableRow(this)

            val codeTextView = TextView(this)
            codeTextView.text = gunsList[i].codeGoods.toString()
            codeTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            codeTextView.setPadding(8, 8, 8, 8)
            row.addView(codeTextView)

            val nameTextView = TextView(this)
            nameTextView.text = gunsList[i].name
            nameTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
            nameTextView.setPadding(8, 8, 8, 8)
            row.addView(nameTextView)

            val categoriesTextView = TextView(this)
            categoriesTextView.text = gunsList[i].category
            categoriesTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)
            categoriesTextView.setPadding(8, 8, 8, 8)
            row.addView(categoriesTextView)

            val priceTextView = TextView(this)
            priceTextView.text = gunsList[i].price.toString()
            priceTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            priceTextView.setPadding(8, 8, 8, 8)
            row.addView(priceTextView)

            val availabilityTextView = TextView(this)
            availabilityTextView.text = if (gunsList[i].isAvailable) "Yes" else "No"
            availabilityTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            availabilityTextView.setPadding(8, 8, 8, 8)
            row.addView(availabilityTextView)

            findViewById<TableLayout>(R.id.table).addView(row)
        }
    }
}