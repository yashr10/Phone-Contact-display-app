package com.example.unicodetask2

import android.provider.BaseColumns

object DetailContract {

    internal const val TABLE_NAME = "detail"

    object Columns {
        // CREATE TABLE details (_id INTEGER PRIMARY KEY NOT NULL , Name TEXT NOT NULL , BirthDate
        //INTEGER , PhoneNumber LONG);
        const val ID =  BaseColumns._ID
        const val NAME = "Name"
        const val BIRTHDATE = "BirthDate"
        const val PHONENUMBER = "PhoneNumber"
    }



}