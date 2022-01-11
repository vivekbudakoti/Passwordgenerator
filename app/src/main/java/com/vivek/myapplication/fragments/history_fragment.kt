package com.vivek.myapplication.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vivek.myapplication.R
import com.vivek.myapplication.adapters.HistoryAdapter
import com.vivek.myapplication.model.Hist

class history_fragment : Fragment() {

    lateinit var recyclerHistory : RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter : HistoryAdapter

    val HistInfoList = arrayListOf<Hist>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     var view = inflater.inflate(R.layout.fragment_history, container, false)

        recyclerHistory = view.findViewById(R.id.recyclerHistory)
        layoutManager = LinearLayoutManager(activity)

        HistInfoList.add(Hist("vi79xh]089h9"));
        HistInfoList.add(Hist("xni8910{];'279dho"));
        HistInfoList.add(Hist("h80shjo["));
        HistInfoList.add(Hist("0shjo["));
        HistInfoList.add(Hist("bupo=270';"));

        recyclerAdapter = HistoryAdapter(activity as Context, HistInfoList)

        recyclerHistory.adapter = recyclerAdapter
        recyclerHistory.layoutManager = layoutManager

                return view
    }

}