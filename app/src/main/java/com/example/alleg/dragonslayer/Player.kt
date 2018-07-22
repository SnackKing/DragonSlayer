package com.example.alleg.dragonslayer

object Player : Human() {

    private var inv:Inventory = Inventory()

    init {
        health = 100
        maxHealth = 100
    }
    fun addToInventory(toAdd:Item){
        if(toAdd.name == "coins"){
            addOrSubtractCoinsFromInventory(toAdd.amount)
        }
        else {
            inv.addItem(toAdd)
        }
    }

    /**
     * Pass in negative number to subtract coins from inv
     */
    fun addOrSubtractCoinsFromInventory(numCoins:Int){
        inv.numCoins = numCoins
    }

    fun removeHealth(toRemove:Int){
        health -= 10;

    }



}
