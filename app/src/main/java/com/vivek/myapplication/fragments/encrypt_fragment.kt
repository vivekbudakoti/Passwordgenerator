package com.vivek.myapplication.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.vivek.myapplication.R
import java.security.Key


class encrypt_fragment : Fragment() {

    lateinit var edt_enter : EditText
    lateinit var edt_generated :EditText
    lateinit var btn_encrypted : Button
    lateinit var img_copy : ImageView

    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData

    lateinit var KeyPhone : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_encrypt, container, false)

        myClipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        edt_enter = view.findViewById(R.id.edt_entertxtFE)
        edt_generated = view.findViewById(R.id.edt_generated_passFE)
        btn_encrypted= view.findViewById(R.id.btn_encrypt_generate)
        img_copy = view.findViewById(R.id.img_copyclipFE)

        //for copying button in generated encryptedpassword
        img_copy.setOnClickListener{
            myClip = ClipData.newPlainText("text", edt_generated.text)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(activity as Context,"Password: \"${edt_generated.text.toString()}\"\ncopied successfully",
                Toast.LENGTH_SHORT).show()

        }

        btn_encrypted.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("MySharedPref",
                AppCompatActivity.MODE_PRIVATE
            )
            KeyPhone = sharedPreferences.getString("keyPhone","9182374650").toString()
            if(edt_enter.text.length>10){
                Toast.makeText(activity as Context,"Max 10 characters are allowed!",Toast.LENGTH_SHORT).show()
            }
            else{
                var encrptxt= encrypt(edt_enter.text.toString(),KeyPhone)
                edt_generated.setText("$encrptxt",TextView.BufferType.EDITABLE)
            }

        }

        return view
    }

    fun encrypt(name:String,num:String):String?{
        val characters_name = name.toCharArray()
        val characters_num = num.toCharArray()
        for(i in characters_name.indices){
            var value_name : Int = characters_name[i].toInt()

            if(value_name>64 && value_name<130){
                value_name = value_name - 60
            }

            val value_num : Int = characters_num[i].toInt()
            val result : Int = (value_name+value_num)
            characters_name[i] = result.toChar()
        }

        return String(characters_name)
    }



}