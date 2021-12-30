package com.vivek.myapplication.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.vivek.myapplication.R
import kotlin.random.Random

class Generate_fragment : Fragment() ,  AdapterView.OnItemSelectedListener{

    lateinit var edt_password : EditText
    lateinit var img_copy_clip : ImageView

    lateinit var btn_generate : Button
    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData
    lateinit var copyText : String
    var selectId : String ="6"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_generate, container, false)

        myClipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val spinner = view.findViewById<View>(R.id.spinner2) as Spinner


        btn_generate = view.findViewById(R.id.btn_Generate)
        edt_password = view.findViewById(R.id.edt_pass)
        img_copy_clip = view.findViewById(R.id.img_copy_clip)




// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            activity as Context,
            R.array.pass_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            spinner.onItemSelectedListener = this
            spinner.adapter = adapter
        }


        //CopyText button
        img_copy_clip.setOnClickListener{
            myClip = ClipData.newPlainText("text", edt_password.text)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(activity as Context,"Password: \"${edt_password.text.toString()}\"\ncopied successfully",Toast.LENGTH_SHORT).show()
        }


        btn_generate.setOnClickListener {

            if(selectId == "6"){
                PassCodeGenerate_6()

            }
            else if(selectId == "10"){
                PassCodeGenerate_10()
            }
            else if(selectId == "12"){
                PassCodeGenerate_12()
            }
            else if(selectId == "14"){
                PassCodeGenerate_14()
            }
            else{
                PassCodeGenerate_8()
            }
        }
        return view
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

        var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}"
        var ss =shuffle(sstring)
        edt_password.setText(ss, TextView.BufferType.EDITABLE)
        copyText = ss.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_8()  {

        var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}"
        var ss =shuffle(sstring)
        edt_password.setText(ss, TextView.BufferType.EDITABLE)
        copyText = ss.toString()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_10()  {

        var sstring = "${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        edt_password.setText(ss, TextView.BufferType.EDITABLE)
        copyText = ss.toString()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_12()  {

        var sstring = "${Pass_Capital()}${Pass_Special()}${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        edt_password.setText(ss, TextView.BufferType.EDITABLE)
        copyText = ss.toString()

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun PassCodeGenerate_14()  {

        var sstring = "${Pass_Num()}${Pass_Small()}${Pass_Capital()}${Pass_Special()}${Pass_Capital()}${Pass_Small()}${Pass_Capital()}${Pass_Num()}${Pass_Special()}${Pass_Num()}${Pass_Special()}${Pass_Small()}${Pass_Num()}${Pass_Small()}"
        var ss =shuffle(sstring)
        edt_password.setText(ss, TextView.BufferType.EDITABLE)
        copyText = ss.toString()

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

    //for working of selecting item on spinner
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val items = parent?.getItemAtPosition(position).toString()
        selectId = items
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectId="6"
    }
}