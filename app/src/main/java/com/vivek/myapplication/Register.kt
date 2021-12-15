package com.vivek.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.util.*
import android.app.TimePickerDialog
import android.text.format.DateFormat.is24HourFormat
import android.widget.EditText
import android.widget.TextView
import java.text.DateFormat


class Register : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var edt_dob : EditText
    lateinit var calender_icon : ImageView
     var year = 0;
   var month = 0;
     var day = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        calender_icon = findViewById(R.id.img_calender_logo)
        edt_dob = findViewById(R.id.edt_dob)

        calender_icon.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(this@Register, this@Register, year, month, day)
            datePickerDialog.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var myMonth = month+1
        val c = Calendar.getInstance()
        edt_dob.setText("$dayOfMonth/$myMonth/$year",TextView.BufferType.EDITABLE)
    }
}