package com.vivek.myapplication.fragments

import android.app.Activity
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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vivek.myapplication.R
import com.vivek.myapplication.adapters.SaveAdapter
import com.vivek.myapplication.model.Hist
import com.vivek.myapplication.model.Save
import java.lang.reflect.Field


class save_fragment : Fragment() {

    lateinit var  RecyclerSave :RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter : SaveAdapter


    val SaveInfoList = arrayListOf<Save>()

    lateinit var floatingAddButton : FloatingActionButton
    val db = Firebase.firestore
    lateinit var userId :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_save, container, false)

        val sh = activity?.getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)
        userId = sh?.getString("userId","").toString()

        RecyclerSave = view.findViewById(R.id.recyclerSave)
        layoutManager = LinearLayoutManager(activity)

        db.collection("PassData").document(userId).get().addOnSuccessListener {

            if(it!=null){
                if(it["UserData"]!=null){
                    val result = it["UserData"] as ArrayList<*>
                    if(result!=null){
                        result.reverse()
                        for(each in result){
                            val data = each as MutableMap<*, *>

                            SaveInfoList.add(Save(
                                data["ForWhich"] as String,
                                data["Password"] as String))
                            Toast.makeText(activity as Context,"${data}",Toast.LENGTH_SHORT).show()
                            recyclerAdapter = SaveAdapter(activity as Context,SaveInfoList)
                            RecyclerSave.adapter = recyclerAdapter
                            RecyclerSave.layoutManager = layoutManager
                        }
                    }
                }


            }



        }.addOnFailureListener {
            Toast.makeText(activity as Context,"Floating pressed",Toast.LENGTH_SHORT).show()
        }






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



            val user_data = hashMapOf(
                "ForWhich" to edt_typeD.text.toString(),
                "Password" to edt_passD.text.toString()
            )
            Toast.makeText(activity as Context,"{${edt_typeD.text.toString()} and ${edt_passD.text.toString()}}",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            db.collection("PassData").document(userId).update("UserData",FieldValue.arrayUnion(user_data))
                .addOnCompleteListener {
                Toast.makeText(activity as Context,"Added",Toast.LENGTH_SHORT).show()
                    SaveInfoList.add(Save(
                        edt_typeD.text.toString()
                      ,
                        edt_passD.text.toString()
                    ))
                    SaveInfoList.reverse()
                //    recyclerAdapter.notifyDataSetChanged()

                }
                .addOnFailureListener {
                    Toast.makeText(activity as Context,"Failed",Toast.LENGTH_SHORT).show()
            }
        }



    }
}