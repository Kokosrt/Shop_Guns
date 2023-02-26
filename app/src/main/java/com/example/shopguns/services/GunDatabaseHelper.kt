package com.example.shopguns.services

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.shopguns.models.Gun


class GunDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "guns_database"
        private const val TABLE_NAME = "guns"

        // Колонки таблиці guns
        private const val GUNS_ID = "guns_id"
        private const val CODE_GOODS = "code_goods"
        private const val NAME_GUNS = "name_guns"
        private const val CATEGORIES = "categories"
        private const val PRICE = "price"
        private const val AVAILABILITY = "availability"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_GUNS_TABLE = ("CREATE TABLE $TABLE_NAME ("
                + "$GUNS_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$CODE_GOODS INTEGER NOT NULL,"
                + "$NAME_GUNS TEXT NOT NULL,"
                + "$CATEGORIES TEXT NOT NULL,"
                + "$PRICE INTEGER DEFAULT 1000,"
                + "$AVAILABILITY INTEGER DEFAULT 1,"
                + "UNIQUE($CODE_GOODS))")
        db.execSQL(CREATE_GUNS_TABLE)
        if (getGunsCount(db) == 0){
        addGuns()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    @SuppressLint("Range")
    fun getGunsByAvailability(availability: Boolean): List<Gun> {
        val guns = mutableListOf<Gun>()
        val db = this.readableDatabase

        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $AVAILABILITY = ?"
        val cursor = db.rawQuery(selectQuery, arrayOf(if (availability) 1.toString() else 0.toString()))



        if (cursor.moveToFirst()) {
            do {
                val gun = Gun(
                    cursor.getLong(cursor.getColumnIndex(GUNS_ID)),
                    cursor.getInt(cursor.getColumnIndex(CODE_GOODS)),
                    cursor.getString(cursor.getColumnIndex(NAME_GUNS)),
                    cursor.getString(cursor.getColumnIndex(CATEGORIES)),
                    cursor.getInt(cursor.getColumnIndex(PRICE)),
                    cursor.getInt(cursor.getColumnIndex(AVAILABILITY)) == 1
                )
                guns.add(gun)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return guns
    }
    @SuppressLint("Range")
    fun getGunsAll(): List<Gun> {
        val guns = mutableListOf<Gun>()
        val db = this.readableDatabase

        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val gun = Gun(
                    cursor.getLong(cursor.getColumnIndex(GUNS_ID)),
                    cursor.getInt(cursor.getColumnIndex(CODE_GOODS)),
                    cursor.getString(cursor.getColumnIndex(NAME_GUNS)),
                    cursor.getString(cursor.getColumnIndex(CATEGORIES)),
                    cursor.getInt(cursor.getColumnIndex(PRICE)),
                    cursor.getInt(cursor.getColumnIndex(AVAILABILITY)) == 1
                )
                guns.add(gun)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return guns
    }
    //перевіряємо, чи база даних пуста
    private fun getGunsCount(db: SQLiteDatabase?): Int {
        val countQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db?.rawQuery(countQuery, null)
        val count = cursor?.count ?: 0
        cursor?.close()
        return count
    }

    fun addGuns() { // додавання зброї
        val gun1 = Gun(1234, "Colt 1911", "Pistols", 5000, true)
        val gun2 = Gun(5678, "M4A1", "Assault Rifles", 10000, true)
        val gun3 = Gun(9101, "SPAS-12", "Shotguns", 8000, false)
        val gun4 = Gun(9201, "K-90", "Knife", 6000, true)

        val db = this.writableDatabase

        val values = ContentValues()
        values.put(CODE_GOODS, gun1.codeGoods)
        values.put(NAME_GUNS, gun1.name)
        values.put(CATEGORIES, gun1.category)
        values.put(PRICE, gun1.price)
        values.put(AVAILABILITY, gun1.isAvailable)

        db.insert(TABLE_NAME, null, values)

        values.put(CODE_GOODS, gun2.codeGoods)
        values.put(NAME_GUNS, gun2.name)
        values.put(CATEGORIES, gun2.category)
        values.put(PRICE, gun2.price)
        values.put(AVAILABILITY, gun2.isAvailable)

        db.insert(TABLE_NAME, null, values)

        values.put(CODE_GOODS, gun3.codeGoods)
        values.put(NAME_GUNS, gun3.name)
        values.put(CATEGORIES, gun3.category)
        values.put(PRICE, gun3.price)
        values.put(AVAILABILITY, gun3.isAvailable)

        db.insert(TABLE_NAME, null, values)
        values.put(CODE_GOODS, gun4.codeGoods)
        values.put(NAME_GUNS, gun4.name)
        values.put(CATEGORIES, gun4.category)
        values.put(PRICE, gun4.price)
        values.put(AVAILABILITY, gun4.isAvailable)

        db.insert(TABLE_NAME, null, values)

        db.close()
    }
}