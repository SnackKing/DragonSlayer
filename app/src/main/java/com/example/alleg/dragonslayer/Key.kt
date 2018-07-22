package com.example.alleg.dragonslayer

import org.json.JSONObject

class Key(src:JSONObject):Item(src) {
    val id:Int
    init{
        name = src.getString("name")
        weight = 0
        description = src.getString("description")
        id = src.getInt("chestId")

    }
}