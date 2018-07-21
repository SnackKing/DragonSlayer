package com.example.alleg.dragonslayer

import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList
import kotlinx.android.synthetic.main.content_main.*


class EnvironmentAction(var src:JSONArray) {

   fun execute(){
       var i = 0;
       while(i < src.length()){
           val currentAction:JSONObject = src.getJSONObject(i)
           val type = currentAction.getString("type")
           when(type){
               "OUTPUT"-> executeOutput(currentAction)
               "ADD"-> executeAdd(currentAction)
               "OPEN"->executeOpen(currentAction)
               "TAKE"->executeTake(currentAction)
               "TALK"->executeTalk(currentAction)
           }

           i++
       }

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