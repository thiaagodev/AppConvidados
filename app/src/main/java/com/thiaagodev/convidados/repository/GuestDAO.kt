package com.thiaagodev.convidados.repository

import androidx.room.*
import com.thiaagodev.convidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel) : Long

    @Update
    fun update(guest: GuestModel) : Int

    @Delete
    fun delete(id: Int?)

    @Query("SELECT * FROM Guest WHERE id = :id")
    fun get(id: Int?): GuestModel?

    @Query("SELECT * FROM Guest")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = :presence")
    fun getByPresence(presence: Int): List<GuestModel>
}