package com.example.unicodetask2

   class DetailClass(name:String, birthDate: String,  phoneNumber: String) {
     private  val name:String = name
     private  val birthDate : String = birthDate
     private val phoneNumber : String = phoneNumber


       fun getName() : String {
           return name
       }

       fun getBirthDate() : String{
           return birthDate
       }

       fun getPhoneNumber() : String{
           return phoneNumber
       }




 }