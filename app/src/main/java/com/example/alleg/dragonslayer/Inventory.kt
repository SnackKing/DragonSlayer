package com.example.alleg.dragonslayer

import java.util.ArrayList

class Inventory {
    internal var items: ArrayList<Item>
    internal var numCoins: Int = 0
    set(valueToAdd){
        field += valueToAdd
    }
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
        numCoins = 0
    }

    fun getItem(name: String): Item? {
        for (current in items) {
            if (current.name == name) {
                return current
            }
        }
        return null
    }
    fun addItem(toAdd:Item){
        items.add(toAdd);
    }
}
