package com.example.unicodetask2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container , fragment1).commit()

    }


}