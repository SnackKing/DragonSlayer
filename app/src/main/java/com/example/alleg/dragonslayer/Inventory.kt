package com.example.alleg.dragonslayer

import java.util.ArrayList

class Inventory {
    internal var items: ArrayList<Item>
    var keys:ArrayList<Key>
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
        keys = ArrayList()
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
    fun hasKey(id: Int):Boolean{
        var hasKey = false
        for(key in keys){
            if(key.id == id){
                hasKey = true
            }
        }
        return hasKey
    }

    override fun toString(): String {
        var output:String = ""
        for(i in items){
            output += i.name + ", "
        }
        output += "\n"
        for(i in keys){
            output += i.name + ", "
        }
        return output
    }

}
