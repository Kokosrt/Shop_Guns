package com.example.shopguns.models

class Gun {
    var id: Long = 0
    var codeGoods: Int = 0
    var name: String? = null
    var category: String? = null
    var price: Int = 0
    var isAvailable: Boolean = false

    constructor()

    constructor(
        codeGoods: Int,
        name: String,
        category: String,
        price: Int,
        isAvailable: Boolean
    ) {
        this.codeGoods = codeGoods;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    constructor(
        id: Long,
        codeGoods: Int,
        name: String,
        category: String,
        price: Int,
        isAvailable: Boolean
    ) {
        this.id = id;
        this.codeGoods = codeGoods;
        this.name = name;
        this.category = category;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    override fun toString(): String {
        return "[  $name  |  $category  |  $price$  ]"
    }
}