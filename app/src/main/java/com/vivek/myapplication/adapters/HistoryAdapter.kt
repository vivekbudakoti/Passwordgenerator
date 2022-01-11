package com.vivek.myapplication.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.vivek.myapplication.R

import com.vivek.myapplication.model.Hist
import java.util.ArrayList

class HistoryAdapter(val context: Context, val itemList: ArrayList<Hist>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_history_single, parent, false)
        myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        return HistoryViewHolder(view)
    }

    class HistoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val edt_history_pass:EditText = view.findViewById(R.id.edt_history)
        val img_copy : ImageView = view.findViewById(R.id.img_hist_copy)
        val img_delete : ImageView = view.findViewById(R.id.img_hist_delete)

    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.edt_history_pass.setText(item.hist_pass,TextView.BufferType.EDITABLE)
        holder.img_copy.setOnClickListener {
            myClip =ClipData.newPlainText("text",item.hist_pass)
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(context,"Password: \"${holder.edt_history_pass.text.toString()}\"\ncopied successfully",
                Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}