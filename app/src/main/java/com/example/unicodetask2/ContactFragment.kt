package com.example.unicodetask2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ContactFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var contactList = ArrayList<ContactDetails>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_contact, container, false)

        contactList = arguments?.getSerializable("contactlist") as ArrayList<ContactDetails>


        val contact_recyclerView : RecyclerView = view.findViewById(R.id.contactRecyclerView)

        contact_recyclerView.layoutManager = LinearLayoutManager(activity)

        contact_recyclerView.adapter = activity?.let { ContactAdapter(contactList , it) }



    return view
    }


}