package com.example.alleg.dragonslayer

import org.json.JSONObject

abstract class Item(src:JSONObject) {
    internal var weight = 1
    internal var name = "object"
    var description = "It is a $name"
    var amount:Int = 1


    companion object {
        val moneyTag:String = "MONEY"
        val weaponTag:String = "WEAPON"
        val keyTag:String = "KEY"
        fun getItemFromObject(src:JSONObject):Item{
            var item:Item? = null
            when(src.getString("type")){
                moneyTag -> item = Money(src)
                weaponTag -> item = Weapon(src)
                keyTag -> item = Key(src)

            }
            return item!!
        }
    }


}
