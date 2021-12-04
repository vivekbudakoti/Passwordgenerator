package com.vivek.myapplication

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class Passgen_screen : AppCompatActivity() {

    lateinit var txt_password: TextView
    lateinit var btn_generate : Button


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passgen_screen)

        txt_password = findViewById(R.id.txt_Password)
        btn_generate = findViewById(R.id.btn_Generate)

        btn_generate.setOnClickListener {
          PassCodeGenerate()

        }

    }

   @RequiresApi(Build.VERSION_CODES.O)
   fun PassCodeGenerate()  {


       val password = arrayOf(Pass_Capital(),Pass_Small(),Pass_Special(),Pass_Num(),Pass_Num(),Pass_Small())
       val sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Special()}${Pass_Num()}${Pass_Num()}${Pass_Small()}";

       for(i in 0..6){
           val rom = Random.nextInt(0,6)
           val temp = sstring[i]

       }

      txt_password.text = "${Pass_Capital()}${Pass_Small()}${Pass_Special()}${Pass_Num()}\n$sstring"
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