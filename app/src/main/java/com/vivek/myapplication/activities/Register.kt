package com.vivek.myapplication.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vivek.myapplication.R


class Register : AppCompatActivity() {

    lateinit var  btn_reg : Button
    lateinit var edt_name :EditText
    lateinit var edt_phone :EditText
    val db = Firebase.firestore
    var result: String? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edt_name = findViewById(R.id.edt_register_name)
        edt_phone = findViewById(R.id.edt_register_phone)



        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val username = sh.getString("username","")
        val userId = sh.getString("userId","")
        btn_reg = findViewById(R.id.btn_reg_register)

        edt_name.setText(username,TextView.BufferType.EDITABLE)
        if (userId != null) {
            db.collection("PassData").document(userId).get().addOnSuccessListener {
                if(it["Phone Number"]!=null){
                    result = it["Phone Number"] as String
                    edt_phone.setText(result.toString(),TextView.BufferType.EDITABLE)
                }

            }.addOnFailureListener {

            }
        }



        btn_reg.setOnClickListener {

            if(edt_phone.text.length == 10){

                //Firebase firestore data here
                val user = hashMapOf(
                    "id" to userId,
                    "Phone Number" to edt_phone.text.toString(),
                )

                //if a previous user enters
                if(result!=null){
                    db.collection("PassData").document(userId.toString()).update("Phone Number","${edt_phone.text.toString()}").addOnSuccessListener {
                        Toast.makeText(this,"Data Again Updated Successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Passgen_screen::class.java))
                    }.addOnFailureListener {
                        Toast.makeText(this,"Failure. Try Again!",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    // Add a new document with a generated ID
                    db.collection("PassData")
                        .document(userId.toString())
                        .set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this,"Data Uploaded Successful",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, Passgen_screen::class.java))
                        }
                        .addOnFailureListener {e->
                            Toast.makeText(this,"Failure. Try Again!",Toast.LENGTH_SHORT).show()
                        }

                    var sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                    sh.edit().putString("keyPhone",edt_phone.text.toString()).commit()

                }



            }

            else{
                Toast.makeText(this,"Please enter correct 10 digit phone number",Toast.LENGTH_SHORT).show()
            }


        }

    }


}