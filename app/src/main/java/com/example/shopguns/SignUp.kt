package com.example.shopguns

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shopguns.databinding.ActivitySignUpBinding
import com.example.shopguns.models.User
import com.example.shopguns.services.MyPrefs
import com.example.shopguns.services.UserDatabaseHelper

class SignUp : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickBackWin(view: View) {
        if (registration()) {
            finish()
        }
    }

    private fun registration(): Boolean {
        // Отримання введених даних користувача з текстових полів
        val firstName = bindingClass.textName.text.toString()
        val lastName = bindingClass.textLastName.text.toString()
        val login = bindingClass.textLogin.text.toString()
        val password = bindingClass.textPassword.text.toString()

        // Створення об'єкту User з введеними даними
        val user = User(firstName, lastName, login, password)

        // Збереження користувача в базі даних
        val userDbHelper = UserDatabaseHelper(this)
        val myPrefs = MyPrefs(this)

        return if (userDbHelper.isLoginAvailable(login)) {//перевірка чи не зайнятий логін
            val userId = userDbHelper.addUser(user)

            // Збереження інформації про авторизацію в MyPrefs
            myPrefs.userId = userId
            myPrefs.isAuthorised = true
            true
        } else {
            Toast.makeText(this, "Такий логін вже використано!", Toast.LENGTH_SHORT).show()
            false
        }

    }
}