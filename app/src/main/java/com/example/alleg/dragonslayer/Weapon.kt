package com.example.alleg.dragonslayer

import org.json.JSONObject

class Weapon(src:JSONObject): Item() {
    var attack:Int = 0;
    init {
        weight = src.getInt("weight")
        name = src.getString("name")
        description = src.getString("description")
        attack = src.getInt("attack")
    }



}