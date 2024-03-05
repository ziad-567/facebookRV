package com.example.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contants_app.R

class ContactsAdapter(val contactList: List<Contact>):RecyclerView.Adapter<ContactsAdapter.MyViewHolder>(){

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val nameTextView:TextView =itemView.findViewById(R.id.name)
        val phoneextView:TextView =itemView.findViewById(R.id.phone)
        fun bind(contact:Contact) {
            nameTextView.text = contact.name
            phoneextView.text = contact.phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = contactList.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
        holder.itemView.setOnClickListener {
            listener?.onClick(contact)
        }
        holder.nameTextView.setOnClickListener{
            nameListener?.onClick(contact)
        }
    }

    fun interface OnContactClickListener{
        fun onClick(contact: Contact)
    }
    var listener: OnContactClickListener? =null


    interface OnNameClickListener{
        fun onClick(contact: Contact)
    }
    var nameListener: OnNameClickListener? =null

}