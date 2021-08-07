package com.example.unicodetask_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class RecyclerAdapter (Mmap: HashMap<String, ArrayList<String>>?): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var map  = HashMap<String, ArrayList<String>>()
    private var list = ArrayList<Map.Entry<String, ArrayList<String>>>()
    init {
        if (Mmap != null) {
            map = Mmap
        }
        list.addAll(map.entries)
    }

    fun filter (filterList :HashMap<String, ArrayList<String>> ){

        map = filterList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)

        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {


        var item = list.get(position)

        holder.listId.append(item.key)
        holder.listBook.append(item.value.toString())

    }



    override fun getItemCount(): Int {
        return list.size
    }



    inner class ViewHolder(itemView :View): RecyclerView.ViewHolder(itemView){
        var listId : TextView
        var listBook : TextView
        init {
            listId = itemView.findViewById(R.id.list_id)
            listBook = itemView.findViewById(R.id.list_book)
        }


    }

}




