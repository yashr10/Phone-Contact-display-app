
package com.example.unicodetask2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView


class ContactAdapter (items : List<ContactDetails> , context : Context ): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {


    private var list : List<ContactDetails> = items
    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.contact_card_view,parent,false)

        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.contact_name.text = list[position].name

        holder.itemView.setOnClickListener(){

            val manager: FragmentManager = ( context as AppCompatActivity).supportFragmentManager
            val transaction = manager.beginTransaction()
            val fragment3 = IndividualContactFragment()
            val bundle = Bundle()

            bundle.putString("nameF" , list[position].name)
            bundle.putString("numberF" , list[position].phone)
            bundle.putString("imageF" , list[position].image.toString())

            fragment3.arguments = bundle
            transaction.replace(R.id.fragment_container , fragment3)
            transaction.addToBackStack("3")
            transaction.commit()
        }




    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        val contact_name: TextView = itemView.findViewById(R.id.contact_name)

    }
}
