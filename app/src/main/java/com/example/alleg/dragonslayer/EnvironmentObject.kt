package com.example.alleg.dragonslayer

import org.json.JSONArray
import org.json.JSONObject

open class EnvironmentObject(val src:JSONObject){
     var destroyAction: EnvironmentAction? = null
     var examineAction: EnvironmentAction?= null
     var openAction: EnvironmentAction? = null
     var takeAction: EnvironmentAction? = null
     var talkAction:EnvironmentAction? = null
     var name:String

    init{
        name = src.getString("objectName")
        var i = 0;
        val actions = src.getJSONObject("interactions")
        if(actions.has("EXAMINE")){
            examineAction = EnvironmentAction(actions.getJSONArray("EXAMINE"))
        }
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


}