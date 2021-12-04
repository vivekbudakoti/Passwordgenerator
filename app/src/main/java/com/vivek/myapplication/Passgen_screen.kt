package com.vivek.myapplication

import android.annotation.SuppressLint
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

class Passgen_screen : AppCompatActivity() {

    lateinit var txt_password: TextView
    lateinit var btn_generate : Button
    lateinit var chk_6_pass : CheckBox
    lateinit var btn_copy : Button
    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData
    lateinit var copyText : String
    lateinit var radiogroup : RadioGroup
    lateinit var radiobutton : RadioButton


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passgen_screen)

        txt_password = findViewById(R.id.txt_Password)
        btn_generate = findViewById(R.id.btn_Generate)
        chk_6_pass = findViewById(R.id.chk_6digi_pass)
        btn_copy = findViewById(R.id.btn_copy)
        radiogroup = findViewById(R.id.rg_pass_dig)
        btn_copy.visibility = View.INVISIBLE

        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

        btn_copy.setOnClickListener {
            myClip = ClipData.newPlainText("text", copyText)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(this,"Password: \"$copyText\"\ncopied successfully",Toast.LENGTH_SHORT).show()
        }




        btn_generate.setOnClickListener {
            btn_copy.visibility = View.VISIBLE
            btn_copy.isVisible
            var selectedID = radiogroup.checkedRadioButtonId
            radiobutton = findViewById(selectedID)
            Toast.makeText(this, radiobutton.getText(),Toast.LENGTH_SHORT).show();
            if(chk_6_pass.isChecked){
                PassCodeGenerate_6()

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

       var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Small()}${Pass_Num()}${Pass_Special()}${Pass_Num()}"
       var ss =shuffle(sstring)
      txt_password.text = ss
       copyText = ss.toString()
    }

    fun PassCodeGenerate_8()  {

        var sstring = "${Pass_Capital()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}${Pass_Num()}${Pass_Special()}${Pass_Num()}"
        var ss =shuffle(sstring)
        txt_password.text = ss
        copyText = ss.toString()
        // println("Current Date is: $formatted")

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

}