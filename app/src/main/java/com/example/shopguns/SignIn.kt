package com.example.shopguns

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shopguns.databinding.ActivitySignInBinding
import com.example.shopguns.services.MyPrefs
import com.example.shopguns.services.UserDatabaseHelper

class SignIn : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickBack(view: View) {
        if (login()) {
            finish()
        }
    }

    private fun login(): Boolean {
        val login = bindingClass.textSignInLogin.text.toString()
        val password = bindingClass.textSignInPass.text.toString()

        val dbHelper = UserDatabaseHelper(this)
        val user = dbHelper.getUserByLogin(login)

        return if (user != null && user.password == password) {//перевірка введених даних
            val myPrefs = MyPrefs(this)
            myPrefs.userId = user.id
            myPrefs.isAuthorised = true
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Авторизовано: " + user, Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(this, "Невірний логін або пароль", Toast.LENGTH_SHORT).show()
            false
        }
    }
}