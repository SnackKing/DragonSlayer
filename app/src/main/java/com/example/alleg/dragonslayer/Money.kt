package com.example.alleg.dragonslayer

import org.json.JSONObject

class Money(src:JSONObject): Item() {
    init{
        name = "coins"
        weight = 0;
        description = "The national currency of this land. Each coin is made of pure silver and has the face of the king printed on it."
        amount = src.getInt("amount")

    }
}