package com.vivek.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import java.util.*
import android.app.TimePickerDialog
import android.content.Intent
import android.text.format.DateFormat.is24HourFormat
import android.widget.*
import java.text.DateFormat


class Register : AppCompatActivity() {

    lateinit var  btn_reg : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btn_reg = findViewById(R.id.btn_reg_register)

        btn_reg.setOnClickListener {

            startActivity(Intent(this,Passgen_screen::class.java))

        }

    }


}