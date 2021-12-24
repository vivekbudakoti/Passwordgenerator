package com.vivek.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Register : AppCompatActivity() {

    lateinit var  btn_reg : Button
    lateinit var edt_name :EditText
    lateinit var edt_phone :EditText
    val db = Firebase.firestore

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edt_name = findViewById(R.id.edt_register_name)
        edt_phone = findViewById(R.id.edt_register_phone)

        val sh = getSharedPreferences("MySharedPref", MODE_APPEND)
        val username = sh.getString("username","")
        val userId = sh.getString("userId","")
        btn_reg = findViewById(R.id.btn_reg_register)

        edt_name.setText(username,TextView.BufferType.EDITABLE)



        btn_reg.setOnClickListener {

            //Firebase firestore data here
            val user = hashMapOf(
                "id" to userId,
                "Phone Number" to edt_phone.text.toString(),
            )

         // Add a new document with a generated ID
            db.collection("PassData")
                .add(user)
                .addOnSuccessListener {
                   Toast.makeText(this,"Data Uploaded Successful",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Passgen_screen::class.java))
                }
                .addOnFailureListener {e->
                    Toast.makeText(this,"Failure. Try Again!",Toast.LENGTH_SHORT).show()
                }

        }

    }


}