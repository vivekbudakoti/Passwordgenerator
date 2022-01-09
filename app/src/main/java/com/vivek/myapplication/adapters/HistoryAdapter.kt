package com.vivek.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vivek.myapplication.R

import com.vivek.myapplication.model.Hist
import java.util.ArrayList

class HistoryAdapter(val context: Context, val itemList: ArrayList<Hist>) : RecyclerView.Adapter<HistoryAdapter.DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_history_single, parent, false)

        return HistoryViewHolder(view)
    }

    class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

}