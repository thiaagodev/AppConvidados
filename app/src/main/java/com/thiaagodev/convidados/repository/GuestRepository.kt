package com.thiaagodev.convidados.repository

import android.content.Context
import com.thiaagodev.convidados.model.GuestModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GuestRepository(context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    suspend fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    suspend fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    suspend fun delete(id: Int?) {
        val guest = get(id)
        guestDataBase.delete(guest)
    }

    suspend fun getAll(): List<GuestModel> {
        return guestDataBase.getAll()
    }

    suspend fun get(id: Int?): GuestModel? {
        return guestDataBase.get(id)
    }

    suspend fun getByPresence(presenceFilter: Int): List<GuestModel> {
        return guestDataBase.getByPresence(presenceFilter)
    }

}