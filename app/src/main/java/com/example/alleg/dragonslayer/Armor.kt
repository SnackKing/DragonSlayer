package com.example.alleg.dragonslayer

import org.json.JSONObject

class Armor(src:JSONObject) : Item(src) {
    init {
        this.description = description
        this.name = name

    }
}
