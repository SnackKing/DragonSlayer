package com.example.alleg.dragonslayer

class Armor(internal var type: EquipmentTypes, internal var defense: Int, description: String, name: String) : Item() {
    init {
        this.description = description
        this.name = name

    }
}
