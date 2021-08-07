 package com.example.unicodetask_1

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.HashMap


 class Fragment2 : Fragment() {


    private var lay : RecyclerView.LayoutManager? = null
    private var adapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if ((activity as MainActivity).supportActionBar != null) {
            val actionBar = (activity as MainActivity).supportActionBar
            setHasOptionsMenu(true)

        }


    }

     var record = HashMap<String, ArrayList<String> >()
    var displayList = HashMap<String, ArrayList<String> >()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_2, container, false)

        record = arguments?.getSerializable("map") as HashMap<String, ArrayList<String>>

        displayList = record
        lay = LinearLayoutManager(activity)

         recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager =lay

        adapter = RecyclerAdapter(displayList)
        recyclerView.adapter = adapter

        view.findViewById<Button>(R.id.button).setOnClickListener(){


            val fragment1 = Fragment1()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container ,fragment1 )
                ?.commit()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            val bundle = Bundle()
            bundle.putSerializable("return" , record)
            fragment1.arguments = bundle
            transaction?.addToBackStack("1")
            transaction?.commit()

        }

      return  view
    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_fragment2, menu)

        val item : MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView


        super.onCreateOptionsMenu(menu, inflater)
    }*/


}