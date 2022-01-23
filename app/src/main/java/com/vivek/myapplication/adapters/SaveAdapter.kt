package com.vivek.myapplication.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vivek.myapplication.R
import com.vivek.myapplication.model.Save

class SaveAdapter(val context:Context,val itemlist:ArrayList<Save>): RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

    lateinit var myClipboard : ClipboardManager
    lateinit var myClip: ClipData
    val db = Firebase.firestore


    lateinit var userId :String

    class SaveViewHolder(view: View): RecyclerView.ViewHolder(view){
        val edt_forwhich : EditText = view.findViewById(R.id.edt_save_forwhich)
        val edt_password : EditText = view.findViewById(R.id.edt_save_pass)
        val img_copyforwhich : ImageView = view.findViewById(R.id.img_save_copyForWhich)
        val img_copypass : ImageView =view.findViewById(R.id.img_save_copyPass)
        val img_delete : ImageView = view.findViewById(R.id.img_save_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_save_single, parent, false)
        myClipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val sh = context.getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)
        userId = sh.getString("userId","")!!

        return SaveViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
      val item = itemlist[position]
        holder.edt_forwhich.setText(item.Forwhich,TextView.BufferType.EDITABLE)
        holder.edt_password.setText(item.Password,TextView.BufferType.EDITABLE)
        holder.img_copyforwhich.setOnClickListener {
            myClip =ClipData.newPlainText("text",holder.edt_forwhich.text.toString())
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(context,"For which: \"${holder.edt_forwhich.text.toString()}\"\ncopied successfully",
                Toast.LENGTH_SHORT).show()
        }

        holder.img_delete.setOnClickListener {

            val user_data : MutableMap<String, Any> = HashMap()
            user_data["ForWhich"] = item.Forwhich
            user_data["Password"] = item.Password


            db.collection("PassData").document(userId).update("UserData", FieldValue.arrayRemove(user_data)).addOnSuccessListener {
                itemlist.remove(item)
                notifyDataSetChanged()
                Toast.makeText(context,"Delete clicked",
                    Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }.addOnFailureListener {

                Toast.makeText(context,"failed Delete clicked",
                    Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }


        }
        holder.img_copypass.setOnClickListener {
            myClip =ClipData.newPlainText("text",holder.edt_password.text.toString())
            myClipboard.setPrimaryClip(myClip)

            Toast.makeText(context,"For which: \"${holder.edt_password.text.toString()}\"\ncopied successfully",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

}