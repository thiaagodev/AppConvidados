package com.thiaagodev.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.thiaagodev.convidados.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {

            if(!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean {
       return try {
           val db = guestDataBase.writableDatabase

           val values = ContentValues()
           val presence = if (guest.presence) 1 else 0

           values.put("name", guest.name)
           values.put("presence", presence)


           db.insert("Guest", null, values)

           true
       } catch (e: Exception) {
           false
       }
    }
}