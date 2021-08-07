package com.example.unicodetask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment2 : Fragment() {

    private var lay : RecyclerView.LayoutManager? = null
    private var adapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private lateinit var recyclerView: RecyclerView
    private var detailArrayList = ArrayList<DetailClass>()
    private  lateinit var dbHandler : AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view=  inflater.inflate(R.layout.fragment_2, container, false)

        lay = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = lay

       dbHandler = AppDataBase(requireContext())

        detailArrayList = dbHandler.readDetail()

        adapter = RecyclerAdapter(detailArrayList )
        recyclerView.adapter = adapter

        return view
    }


}