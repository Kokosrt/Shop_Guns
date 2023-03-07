package com.example.shopguns

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopguns.databinding.ActivityBackGunsBinding
import com.example.shopguns.databinding.ActivityGunsSelleryBinding
import com.example.shopguns.models.Gun
import com.example.shopguns.services.GunDatabaseHelper
import com.example.shopguns.services.GunsAdapter

class BackGuns : AppCompatActivity() {

    private lateinit var bindingClass: ActivityBackGunsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var dbHelper: GunDatabaseHelper
    private lateinit var gunsList: List<Gun>
    private lateinit var filteredGunsList: MutableList<Gun>
    private lateinit var selectedGun:Gun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityBackGunsBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        dbHelper = GunDatabaseHelper(this)
        gunsList = dbHelper.getGunsByAvailability(false)
        filteredGunsList = gunsList.toMutableList()

        recyclerView = bindingClass.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GunsAdapter(filteredGunsList) { position ->
            onGunItemClick(position)
            searchView.setQuery("", false) // очищуємо поле пошуку
        }

        // Додамо функціонал для пошуку
        searchView = bindingClass.Search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredGunsList.clear()
                gunsList.forEach {
                    if (it.name!!.contains(newText.orEmpty(), ignoreCase = true)) {
                        filteredGunsList.add(it)
                    }
                }
                recyclerView.adapter?.notifyDataSetChanged()

                // Встановлюємо видимість recyclerView в залежності від наявності тексту у searchView
                recyclerView.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE

                return true
            }
        })

        // Початково робимо recyclerView невидимим
        recyclerView.visibility = View.GONE
    }

    fun onGunItemClick(position: Int) {
        selectedGun = filteredGunsList[position]

        // Встановлюємо ціну в текстове поле
        bindingClass.tNameGun.text = selectedGun.name
        bindingClass.tPriceGun.text = selectedGun.price.toString()

        // Очищаємо searchView
        searchView.setQuery("", false)
        searchView.clearFocus()
    }

    fun onClickComplete(view: View) {
        dbHelper.updateGunAvailability(selectedGun.id, true)
        Toast.makeText(this, "Повернено: " + selectedGun.name, Toast.LENGTH_SHORT).show()
        finish()
    }
}