package com.example.alleg.dragonslayer

import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import kotlinx.android.synthetic.main.content_main.*


class EnvironmentAction(var src:JSONArray) {

   fun execute(obj:EnvironmentObject):String{
       var i = 0;
       var output = ""
       while(i < src.length()){
           val currentAction:JSONObject = src.getJSONObject(i)
           val type = currentAction.getString("type")
           when(type){
               "OUTPUT"-> output += executeOutput(currentAction)
               "ADD"-> executeAdd(currentAction,obj)
               "OPEN"->executeOpen(currentAction,obj)
               "TALK"->output += executeTalk(currentAction,obj)
               "CHANGE_DESCRIPTION" -> obj.description = currentAction.getString("NEW_DESCRIPTION")
               "DAMAGE_PLAYER" -> {
                    val damage = currentAction.getInt("DAMAGE_PLAYER")
                    Player.removeHealth(damage)
                   output += "\n You take " + damage + " damage"
               }
           }

           i++
       }
       return output

   }
    fun executeOutput(currentAction:JSONObject):String{
        return currentAction.getString("output")
    }
    fun executeAdd(currentAction:JSONObject,obj:EnvironmentObject){
        val jsonItemToAdd:JSONObject = currentAction.getJSONObject(addTag)
        val itemToAdd = Item.Companion.getItemFromObject(jsonItemToAdd)
        Player.addToInventory(itemToAdd)

    }
    fun executeOpen(currentAction:JSONObject,obj:EnvironmentObject){
        obj.open = true
        obj.locked = false
    }
    fun executeTake(currentAction:JSONObject,obj:EnvironmentObject):String {
        return ""
    }
    fun executeTalk(currentAction:JSONObject,obj:EnvironmentObject):String {
        return ""
    }

    companion object {
        val addTag:String = "ADD_TO_INVENTORY"
    }


}