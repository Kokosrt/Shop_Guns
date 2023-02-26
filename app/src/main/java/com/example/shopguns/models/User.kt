package com.example.shopguns.models

class User {
    // клас який представляє дані користувача
    var id: Long = 0
    var firstName: String? = null
    var lastName: String? = null
    var login: String? = null
    var password: String? = null


    constructor() {}
    //створення об'єкта
    constructor(firstName: String?, lastName: String?, login: String?, password: String?) {
        this.firstName = firstName
        this.lastName = lastName
        this.login = login
        this.password = password
    }

    constructor(
        id: Long,
        firstName: String?,
        lastName: String?,
        login: String,
        password: String?
    ) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.login = login
        this.password = password
    }
// виведення у текстовому вигляді
    override fun toString(): String {
        return "$firstName $lastName"
    }


}
