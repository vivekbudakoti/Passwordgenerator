package com.vivek.myapplication.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Toast.makeText(activity as Context,"Floating pressed",Toast.LENGTH_SHORT).show()
        }

        return view
    }
}