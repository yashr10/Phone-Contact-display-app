package com.example.unicodetask2

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class IndividualContactFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private lateinit var  name : TextView
    private lateinit var number : TextView
    private lateinit var picture : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_individual_contact, container, false)

        name = view.findViewById(R.id.nameF)
        number=view.findViewById(R.id.numberF)
        picture = view.findViewById(R.id.imageF)

        name.append(arguments?.getString("nameF"))
        number.append(arguments?.getString("numberF"))
/*
        val a = arguments?.getString("imageF")

        if (a != null){
            picture.setImageURI(Uri.parse(a))
        }
        else{
            picture.setImageResource(R.drawable.ic_baseline_person_24)
        }*/


        return view
    }





}