package com.vivek.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.vivek.myapplication.R


class encrypt_fragment : Fragment() {

    lateinit var edt_enter : EditText
    lateinit var edt_generated :EditText
    lateinit var btn_encrypted : Button
    lateinit var img_copy : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_encrypt, container, false)

        edt_enter = view.findViewById(R.id.edt_entertxtFE)
        edt_generated = view.findViewById(R.id.edt_generated_passFE)
        btn_encrypted= view.findViewById(R.id.btn_encrypt_generate)
        img_copy = view.findViewById(R.id.img_copyclipFE)

        btn_encrypted.setOnClickListener {
           var encrptxt= encrypt(edt_enter.text.toString(),"8095678486")
            edt_generated.setText("$encrptxt",TextView.BufferType.EDITABLE)
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


    fun decrypt(name:String,num:String):String?{
        val characters_name = name.toCharArray()
        val characters_num = num.toCharArray()
        for(i in characters_name.indices){
            var value_name : Int = characters_name[i].toInt() - characters_num[i].toInt()


            val result : Int = value_name + 60
            characters_name[i] = result.toChar()
        }

        return String(characters_name)
    }

}