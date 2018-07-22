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
               "OUTPUT"-> executeOutput(currentAction)
               "ADD"-> executeAdd(currentAction)
               "OPEN"->executeOpen(currentAction)
               "UNLOCK"->executeOpen(currentAction)
               "TALK"->executeTalk(currentAction)
               "CHANGE_DESCRIPTION" -> obj.description = currentAction.getString("NEW_DESCRIPTION")
               "DAMAGE_PLAYER" -> Player.removeHealth(currentAction.getInt("DAMAGE_PLAYER"))
           }

           i++
       }
       return output

   }
    fun executeOutput(currentAction:JSONObject):String{
        return currentAction.getString("output")
    }
    fun executeAdd(currentAction:JSONObject){
        val jsonItemToAdd:JSONObject = currentAction.getJSONObject(addTag)
        val itemToAdd = Item.Companion.getItemFromObject(jsonItemToAdd)
        Player.addToInventory(itemToAdd)

    }
    fun executeOpen(currentAction:JSONObject){

    }
    fun executeTake(currentAction:JSONObject){

    }
    fun executeTalk(currentAction:JSONObject){

    }

    companion object {
        val addTag:String = "ITEM_TO_ADD"
    }


}