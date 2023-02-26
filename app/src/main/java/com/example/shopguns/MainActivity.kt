package com.example.shopguns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.shopguns.databinding.ActivityMainBinding
import com.example.shopguns.services.MyPrefs
import com.example.shopguns.services.UserDatabaseHelper

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    private lateinit var myPrefs:MyPrefs //клас для збереження своїх налаштувань
    private lateinit var dbHelper:UserDatabaseHelper //клас для керування БД

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        myPrefs = MyPrefs(this)// ініціалізація
        dbHelper = UserDatabaseHelper(this)
        manageButtons()
    }

    override fun onDestroy() {//завершення сесії
        super.onDestroy()
        myPrefs.clear()
    }

    private fun manageButtons() {//керування кнопками
        if (myPrefs.isAuthorised) {
            bindingClass.bSignIn.visibility = View.GONE
            bindingClass.bSignUp.visibility = View.GONE
            bindingClass.bLogOut.visibility = View.VISIBLE
            bindingClass.bNext.visibility = View.VISIBLE
            val user = dbHelper.getUserById(myPrefs.userId)
            bindingClass.aboutUser.text = user.toString()
        } else {
            bindingClass.bSignIn.visibility = View.VISIBLE
            bindingClass.bSignUp.visibility = View.VISIBLE
            bindingClass.bLogOut.visibility = View.GONE
            bindingClass.bNext.visibility = View.GONE
            bindingClass.aboutUser.text = "Не авторизовано!"
        }
    }

    fun onClickRegist(view: View) {//кнопка регістру
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    fun onClickSignIn(view: View) {//кнопка входу
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }

    fun onClickLogout(view: View) {//кнопка виходу
        myPrefs.clear()
        startActivity(Intent(this,MainActivity::class.java))
    }
    fun onClickNext(view: View){//кнопка далі
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }

}