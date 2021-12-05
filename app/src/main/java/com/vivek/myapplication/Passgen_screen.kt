package com.vivek.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isVisible
import kotlin.random.Random
import android.widget.Spinner
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.ActionBarContainer
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class Passgen_screen : AppCompatActivity(),  AdapterView.OnItemSelectedListener {

    lateinit var txt_password: TextView
    lateinit var btn_generate : Button
    lateinit var chk_6_pass : CheckBox
    lateinit var btn_copy : Button
    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData
    lateinit var copyText : String
    var selectId : String ="6"

    lateinit var toolbar : Toolbar
    lateinit var drawerlayout : DrawerLayout
    lateinit var navigation : NavigationView


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passgen_screen)

        txt_password = findViewById(R.id.txt_Password)
        btn_generate = findViewById(R.id.btn_Generate)
        btn_copy = findViewById(R.id.btn_copy)
        btn_copy.visibility = View.INVISIBLE


        //Toolbar setting
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Hamburger icon setting
        drawerlayout = findViewById(R.id.Drawer)
        navigation = findViewById(R.id.navigation)
        var toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.navigation_open,R.string.navigation_close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager


        val spinner = findViewById<View>(R.id.spinner2) as Spinner

        spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.pass_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }



        btn_copy.setOnClickListener {
            myClip = ClipData.newPlainText("text", copyText)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(this,"Password: \"$copyText\"\ncopied successfully",Toast.LENGTH_SHORT).show()
        }




        btn_generate.setOnClickListener {
            btn_copy.visibility = View.VISIBLE
            btn_copy.isVisible

            if(selectId == "6"){
                PassCodeGenerate_6()

            }
            else if(selectId == "10"){
                PassCodeGenerate_10()
            }
             else if(selectId == "12"){
                PassCodeGenerate_12()
            }
             else if(selectId == "14"){
                PassCodeGenerate_14()
            }
            else{
               PassCodeGenerate_8()
            }
        }

    }



    fun shuffle(text: String): String? {
        val characters = text.toCharArray()
        for (i in characters.indices) {
            val randomIndex = (Math.random() * characters.size).toInt()
            val temp = characters[i]
            characters[i] = characters[randomIndex]
            characters[randomIndex] = temp
        }
        return String(characters)
    }

   @RequiresApi(Build.VERSION_CODES.O)
   fun PassCodeGenerate_6()  {

       var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}"
       var ss =shuffle(sstring)
      txt_password.text = ss
       copyText = ss.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_8()  {

        var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}"
        var ss =shuffle(sstring)
        txt_password.text = ss
        copyText = ss.toString()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_10()  {

        var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        txt_password.text = ss
        copyText = ss.toString()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_12()  {

        var sstring = "${Pass_Capital()}${Pass_Special()}${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        txt_password.text = ss
        copyText = ss.toString()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_14()  {

        var sstring = "${Pass_Num()}${Pass_Small()}${Pass_Capital()}${Pass_Special()}${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        txt_password.text = ss
        copyText = ss.toString()

    }

    fun Pass_Capital(): Char{

        val rrandom = Random.nextInt(65,90)
        val sstring = rrandom.toChar()

        return sstring
    }

    fun Pass_Small(): Char{

        val rrandom = Random.nextInt(97,122)
        val sstring = rrandom.toChar()

        return sstring
    }

    fun Pass_Special(): Char{

        val rrandom = Random.nextInt(33,47)
        val sstring = rrandom.toChar()

        return sstring
    }

    fun Pass_Num(): Int{
         return Random.nextInt(0,10)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       val items = parent?.getItemAtPosition(position).toString()
       selectId = items
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}