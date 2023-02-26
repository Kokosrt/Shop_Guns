package com.example.shopguns.services
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shopguns.models.User

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//клас керування БД user
    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val DATABASE_VERSION = 1

        // Таблиця "users"
        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_FIRST_NAME = "first_name"
        private const val COLUMN_USER_LAST_NAME = "last_name"
        private const val COLUMN_USER_LOGIN = "login"
        private const val COLUMN_USER_PASSWORD = "password"

        // SQL запит для створення таблиці користувачів
        private const val CREATE_USERS_TABLE = (
                "CREATE TABLE $TABLE_USERS ($COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "$COLUMN_USER_FIRST_NAME TEXT,"
                        + "$COLUMN_USER_LAST_NAME TEXT,"
                        + "$COLUMN_USER_LOGIN TEXT UNIQUE,"
                        + "$COLUMN_USER_PASSWORD TEXT)"
                )
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // При оновленні бази даних можна виконати потрібні дії
    }
    fun addUser(user: User): Long {//додавання користувача
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_FIRST_NAME, user.firstName)
        values.put(COLUMN_USER_LAST_NAME, user.lastName)
        values.put(COLUMN_USER_LOGIN, user.login)
        values.put(COLUMN_USER_PASSWORD, user.password)
        val id = db.insert(TABLE_USERS, null, values)
        db.close()
        return id
    }
    fun isLoginAvailable(login: String): Boolean {//перевірка чи не зайнятий логін
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USER_ID),
            "$COLUMN_USER_LOGIN = ?",
            arrayOf(login),
            null,
            null,
            null,
            null
        )
        val isAvailable = cursor.count == 0
        cursor.close()
        db.close()
        return isAvailable
    }
    @SuppressLint("Range")
    fun getUserById(id: Long): User? {//отримання даних користувача по ID
        val db = readableDatabase
        val cursor = db.query(TABLE_USERS, null, "$COLUMN_USER_ID=?", arrayOf(id.toString()), null, null, null)
        return if (cursor.moveToFirst()) {
            val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME))
            val login = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LOGIN))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
            User(id, firstName, lastName, login, password)
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun getUserByLogin(login: String): User? {// отримання даних користувача по логіну
        val db = readableDatabase
        val cursor = db.query(TABLE_USERS, null, "$COLUMN_USER_LOGIN=?", arrayOf(login), null, null, null)
        val user = if (cursor.moveToFirst()) {
            val id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_ID))
            val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME))
            val password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD))
            User(id, firstName, lastName, login, password)
        } else {
            null
        }
        return user
    }
}