package com.example.unicodetask2


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (detail:  ArrayList<DetailClass>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    private var detailArrayList : ArrayList<DetailClass> = detail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)

        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return detailArrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item : DetailClass= detailArrayList[position]

        holder.name_id.append(item.getName())
        holder.birthDate_id.append(item.getBirthDate())
        holder.phoneNumber_id.append(item.getPhoneNumber())


    }





    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        var name_id : TextView
        var birthDate_id : TextView
        var phoneNumber_id : TextView

        init {
            name_id = itemView.findViewById(R.id.name_card)
            birthDate_id = itemView.findViewById(R.id.birthdate_card)
            phoneNumber_id = itemView.findViewById(R.id.poneNumber_card)
        }

    }
}