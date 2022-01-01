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

class decrypt_fragment : Fragment() {

    lateinit var edt_enter :EditText
    lateinit var edt_generate : EditText
    lateinit var btn_decrypt : Button
    lateinit var img_copyclip : ImageView
    lateinit var clipboardManager: ClipboardManager
    lateinit var myclip :ClipData

    lateinit var KeyPhone : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_decrypt, container, false)

        clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        edt_enter = view.findViewById(R.id.edt_entertxtDE)
        edt_generate = view.findViewById(R.id.edt_generated_passDE)
        btn_decrypt = view.findViewById(R.id.btn_decrypt_generate)
        img_copyclip = view.findViewById(R.id.img_copyclipDE)

        img_copyclip.setOnClickListener {
            myclip = ClipData.newPlainText("text", edt_generate.text)
            clipboardManager.setPrimaryClip(myclip)

            Toast.makeText(activity as Context,"Password: \"${edt_generate.text.toString()}\"\ncopied successfully",
                Toast.LENGTH_SHORT).show()
        }

        btn_decrypt.setOnClickListener {
            val sharedPreferences = requireActivity().getSharedPreferences("MySharedPref",
                AppCompatActivity.MODE_PRIVATE
            )
            KeyPhone = sharedPreferences.getString("keyPhone","9182374650").toString()
            var decrptedPass = decrypt(edt_enter.text.toString(),KeyPhone)
            edt_generate.setText("$decrptedPass",TextView.BufferType.EDITABLE)
        }

        return view
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