package com.example.alleg.dragonslayer

import java.util.ArrayList

class Inventory {
    internal var items: ArrayList<Item>
    val totalWeight: Int
        get() {
            var currentTotal = 0
            for (current in items) {
                currentTotal += current.weight
            }
            return currentTotal
        }

    init {
        items = ArrayList()
    }

    fun getItem(name: String): Item? {
        for (current in items) {
            if (current.name == name) {
                return current
            }
        }
        return null
    }
}
