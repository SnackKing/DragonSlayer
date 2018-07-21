package com.example.alleg.dragonslayer.Utils

import com.example.alleg.dragonslayer.Action

private var examineActions:Array<String> = arrayOf("investigate", "examine", "look at", "inspect", "study", "scan", "probe", "analyze", "evaluate", "survey")
private var destroyActions:Array<String> = arrayOf("destroy", "break", "attack","kill", "obliterate")
private var openActions:Array<String> = arrayOf("open","unlock", "unfasten", "unbolt", "unlatch")
private var takeActions:Array<String> = arrayOf("take", "loot", "gather", "grab", "pickup")
private var talkActions:Array<String> = arrayOf("talk", "communicate", "speak", "ask", "meet")


fun String.getSynonym():Action{
    var returnValue:Action = Action.ERROR
    if(examineActions.contains(this)){
        returnValue = Action.EXAMINE
    }
    else if(destroyActions.contains(this)){
        returnValue = Action.DESTROY
    }
    else if(openActions.contains(this)){
        returnValue = Action.OPEN
    }
    else if(takeActions.contains(this)){
        returnValue = Action.TAKE
    }
    else if(talkActions.contains(this)) {
        returnValue = Action.TALK
    }
    else{
        returnValue = Action.ERROR
    }
    return returnValue
}

