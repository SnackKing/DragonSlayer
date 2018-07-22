package com.example.alleg.dragonslayer

import android.app.PendingIntent.getActivity
import android.media.audiofx.EnvironmentalReverb
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.example.alleg.dragonslayer.Utils.getSynonym

import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_main.*;
import kotlinx.android.synthetic.main.content_main.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    var descrition:String = ""
    var environment = HashMap<String,EnvironmentObject>()
    lateinit var output:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        output = displayText
        parseJson()
       enterButton.setOnClickListener {
           var command:String = commandInput.text.toString().toLowerCase()
           command = filterCommand(command)
           val action:Action = command.substring(0,command.indexOf(' ')).getSynonym()
           val subject =  command.substring(command.indexOf(' ')+1)
           displayText.text = displayText.text.toString() + "\n" + command
           if(action != Action.ERROR) {
               if (environment.containsKey(subject)) {
                   val obj = environment.get(subject)
                   if (obj != null) {
                      val output = obj.execute(action)
                       displayText.text = displayText.text.toString() + "\n" + output
                   }
               }
               else{
                   displayText.text = "That object does not exist"
               }
           }
           else{
               displayText.text = "You can't do that."
           }

       }

    }

    fun loadJSONFromAsset(): String {
        var json: String? = null
        try {
            val stream: InputStream = this.getAssets().open("bedroom.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return json
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun parseJson(){
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val jArray = obj.getJSONArray("objects")
            val formList = ArrayList<HashMap<String, String>>()
            descrition = obj.getString("description")
            output.text = output.text.toString() +  "\n" + descrition
            environment = parseObjects(jArray)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    fun parseObjects(array:JSONArray):HashMap<String,EnvironmentObject>{
        var i = 0;
        var objMap = HashMap<String,EnvironmentObject>()
        while(i < array.length()){
            val currentObj = array.getJSONObject(i)
            objMap.put(currentObj.getString("objectName"),EnvironmentObject(currentObj))
            i++
        }

        return objMap


    }

    fun filterCommand(command:String):String{
        val re = Regex("[^A-Za-z0-9 ]")
        var filteredCommand = command
        filteredCommand = re.replace(command, "").toLowerCase()
        filteredCommand = filteredCommand.replace("the","")
        filteredCommand = filteredCommand.replace("with","")
        filteredCommand = filteredCommand.replace("to","")
        filteredCommand = filteredCommand.replace("up","")



        return filteredCommand
    }
    fun addUpWords(tokens:List<String>,index:Int):String{
        var locIndex = index
        var word:String = ""
        while(locIndex < word.length){
            word += tokens.get(index) + " ";

            locIndex++
        }
        return word
    }

}
