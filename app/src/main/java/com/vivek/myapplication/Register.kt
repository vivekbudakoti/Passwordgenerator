package com.vivek.myapplication

import android.annotation.SuppressLint
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
    lateinit var edt_name :EditText

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edt_name = findViewById(R.id.edt_register_name)

        val sh = getSharedPreferences("MySharedPref", MODE_APPEND)
        val username = sh.getString("username","")
        btn_reg = findViewById(R.id.btn_reg_register)

        edt_name.setText(username,TextView.BufferType.EDITABLE)

        btn_reg.setOnClickListener {

            startActivity(Intent(this,Passgen_screen::class.java))

        }

    }


}