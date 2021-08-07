package com.example.unicodetask_1

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception


class Fragment1 : Fragment() {
    private var record = HashMap<String, ArrayList<String> >()
    private var back = HashMap<String, ArrayList<String> >()

    private lateinit var communicator : Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ((activity as MainActivity).supportActionBar != null) {
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar!!.setDisplayHomeAsUpEnabled(false)
            setHasOptionsMenu(true)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_1, container, false)

        communicator = activity as Communicator

       try {
           back= (arguments?.getSerializable("return") as HashMap<String, ArrayList<String>>?)!!

       }catch (e : Exception){

       }


        record.putAll(back)



        view.findViewById<ImageButton>(R.id.confirm).setOnClickListener(){


            val SAPid = view.findViewById<TextView>(R.id.SAPid)
            val BookName = view.findViewById<TextView>(R.id.BookName)
            val sAPid = SAPid.text.toString()
            val bookname = BookName.text.toString()

            if(SAPid.text.isEmpty()  || BookName.text.isEmpty() ){
                Toast.makeText(activity, "Fill in all the details", Toast.LENGTH_SHORT ).show()
            }
            else {

                if (record.containsKey(sAPid)) {

                    if (record.get(sAPid)?.contains(bookname) == true){

                        Toast.makeText(activity, "The student has already issued this book", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        record.get(sAPid)?.add(bookname)
                        Toast.makeText(activity, "Book added", Toast.LENGTH_SHORT).show()
                        SAPid.setText("")
                        BookName.setText("")
                    }
                }

                else {
                    record.put(sAPid, ArrayList())
                    record.get(sAPid)?.add(bookname)
                    Toast.makeText(activity, "Book added", Toast.LENGTH_SHORT).show()
                    SAPid.setText("")
                    BookName.setText("")
                }
            }
        }

        view.findViewById<ImageButton>(R.id.clear).setOnClickListener(){

            val SAPid = view.findViewById<TextView>(R.id.SAPid)
            val BookName = view.findViewById<TextView>(R.id.BookName)
            val sAPid = SAPid.text.toString()
            val bookname = BookName.text.toString()

            if( SAPid.text.isEmpty()  || BookName.text.isEmpty() ){
                Toast.makeText(activity , "Fill in all the details",Toast.LENGTH_SHORT ).show()
            }
            else {


                if (record.containsKey(sAPid)) {
                    if (record.get(sAPid)?.contains(bookname) == true) {
                        record.get(sAPid)?.remove(bookname)
                        Toast.makeText(activity, "Book removed", Toast.LENGTH_SHORT).show()
                        SAPid.setText("")
                        BookName.setText("")
                        if(record.get(sAPid)!!.isEmpty() == true){

                            record.remove(sAPid)
                        }


                    } else {
                        Toast.makeText(activity,
                            "The student has not issued this book",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(activity, "The student has issued no books", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

        return view

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.search -> {
                if(record.isEmpty()){
                    Toast.makeText(activity, "No student has issued any books", Toast.LENGTH_SHORT).show()

                }
                else {
                    communicator.passDataCom(record)

                }

            }
    }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_fragment1, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }



}