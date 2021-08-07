package com.example.unicodetask_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() ,Communicator  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).commit()


        supportActionBar?.setTitle("Library Management System")
        val actionBar = getSupportActionBar()



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun passDataCom(record: HashMap<String, ArrayList<String>>) {

        val bundle = Bundle()
        bundle.putSerializable("map" , record)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragment2 = Fragment2()
        fragment2.arguments = bundle

        transaction.replace(R.id.fragment_container , fragment2)
        transaction.addToBackStack(" fragment1")
        transaction.commit()
    }
}
