package com.example.alleg.dragonslayer

import org.json.JSONObject

abstract class Item {
    internal var weight = 1
    internal var name = "object"
    var description = "It is a $name"
    var amount:Int = 1


    companion object {
        final val moneyTag:String = "MONEY"
        final val weaponTag:String = "WEAPON"
        fun getItemFromObject(src:JSONObject):Item{
            var item:Item? = null
            when(src.getString("type")){
                moneyTag -> item = Money(src)
                weaponTag -> item = Weapon(src)
            }
            return item!!
        }
    }


}
