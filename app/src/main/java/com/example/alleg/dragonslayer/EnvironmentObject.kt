package com.example.alleg.dragonslayer

import org.json.JSONArray
import org.json.JSONObject

open class EnvironmentObject(val src:JSONObject){
     var destroyAction: EnvironmentAction? = null
     var openAction: EnvironmentAction? = null
     var takeAction: EnvironmentAction? = null
     var talkAction:EnvironmentAction? = null
     var name:String
     var description:String
     var locked = false
     var open = false
     var keyId = -1
     var broken = false

    init{
        name = src.getString("objectName")
        description = src.getString("description")
        var i = 0;
        val actions = src.getJSONObject("interactions")
        if(actions.has("DESTROY")){
            destroyAction = EnvironmentAction(actions.getJSONArray("DESTROY"))
        }
        if(actions.has("OPEN")){
            openAction = EnvironmentAction(actions.getJSONArray("OPEN"))
            locked = src.getBoolean("locked")
            open = src.getBoolean("open")
            keyId = src.getInt("keyId")


        }
        if(actions.has("TAKE")){
            takeAction = EnvironmentAction(actions.getJSONArray("TAKE"))
        }
    }
    fun execute(action:Action):String{
        var output = ""
        when(action){
            Action.EXAMINE -> output = description
            Action.DESTROY -> {
                if(!broken) output = destroyAction?.execute(this) ?:"You can't do that to the " + name else output = name + " is already broken"
                broken = true
            }
            Action.OPEN -> if(Player.inventory.hasKey(keyId) || !locked)output = openAction?.execute(this)?:"You can't do that to the " + name  else  output ="It is locked"

            Action.TALK -> output = talkAction?.execute(this)?:"You can't do that to the " + name
        }
        return output

    }
    fun changeDescription(newDesc:String){
        description = newDesc
    }


}