package com.example.unicodetask2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

private const val TAG = "AppDataBase"
private const val DATABASE_NAME = "details.db"
private const val  DATABASE_VERSION = 1

 class AppDataBase(context: Context) : SQLiteOpenHelper (context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    init {
        Log.d(TAG, "AppDataBase initialising ")

    }

    override fun onCreate(db: SQLiteDatabase) {
        // CREATE TABLE details (_id INTEGER PRIMARY KEY NOT NULL , Name TEXT NOT NULL , BirthDate INTEGER  , PhoneNumber LONG);

        Log.d(TAG, "onCreate starts")

        val sSQL = """
            CREATE TABLE ${DetailContract.TABLE_NAME}(
            ${DetailContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL, 
            ${DetailContract.Columns.NAME} TEXT NOT NULL,
            ${DetailContract.Columns.BIRTHDATE} TEXT,
            ${DetailContract.Columns.PHONENUMBER} TEXT ); """.replaceIndent(newIndent = " ")
            Log.d(TAG,sSQL)
            db.execSQL(sSQL)

    }

    fun addDetails (name : String ,birthDate :String ,phoneNumber:String){

        var db: SQLiteDatabase = this.writableDatabase

        var input = ContentValues()
        input.put(DetailContract.Columns.NAME,name)
        input.put(DetailContract.Columns.BIRTHDATE,birthDate)
        input.put(DetailContract.Columns.PHONENUMBER,phoneNumber)

        db.insert(DetailContract.TABLE_NAME , null , input)
        db.close()

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $DetailContract.TABLE_NAME")
    }

     fun readDetail() : ArrayList<DetailClass>{

         var db : SQLiteDatabase = this.readableDatabase

          var cursorDetail : Cursor = db.rawQuery("SELECT * FROM ${DetailContract.TABLE_NAME}", null)

         var detailClassArrayList =  ArrayList<DetailClass> ()

         if (cursorDetail.moveToFirst()){

             do {

                 detailClassArrayList.add(DetailClass(cursorDetail.getString(1),
                     cursorDetail.getString(2),
                     cursorDetail.getString(3)))
             }while (cursorDetail.moveToNext())

         }

         cursorDetail.close()
         return detailClassArrayList


     }


}