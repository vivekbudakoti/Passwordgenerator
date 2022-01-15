package com.vivek.myapplication.fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vivek.myapplication.R


class save_fragment : Fragment() {

    lateinit var floatingAddButton : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_save, container, false)

        floatingAddButton = view.findViewById(R.id.floating_button)

        floatingAddButton.setOnClickListener {
            alertbox()
            Toast.makeText(activity as Context,"Floating pressed",Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun alertbox(){
        val mdialogView = LayoutInflater.from(activity as Context).inflate(R.layout.mydialog,null)
        val dialogBuilder = AlertDialog.Builder(activity as Context)

        dialogBuilder.setView(mdialogView)

        var edt_typeD:EditText = mdialogView.findViewById(R.id.edt_typeD)
        var edt_passD:EditText = mdialogView.findViewById(R.id.edt_passD)
        var btn_SaveD:Button = mdialogView.findViewById(R.id.btn_saveD)
        val dialog = dialogBuilder.create()
        dialog.show()
        btn_SaveD.setOnClickListener {
            Toast.makeText(activity as Context,"{${edt_typeD.text.toString()} and ${edt_passD.text.toString()}}",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }



    }
}