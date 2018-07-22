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
        }
        if(actions.has("TAKE")){
            takeAction = EnvironmentAction(actions.getJSONArray("TAKE"))
        }
    }
    fun execute(action:Action):String{
        var output = ""
        when(action){
            Action.EXAMINE -> output = description
            Action.DESTROY -> output = destroyAction?.execute(this) ?:"You can't do that to the " + name
            Action.OPEN    -> output = openAction?.execute(this)?:"You can't do that to the " + name
            Action.TALK    -> output = talkAction?.execute(this)?:"You can't do that to the " + name
        }
        return output

    }
    fun changeDescription(newDesc:String){
        description = newDesc
    }


}