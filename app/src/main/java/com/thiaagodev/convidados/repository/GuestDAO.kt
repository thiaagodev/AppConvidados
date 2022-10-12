package com.thiaagodev.convidados.repository

import androidx.room.*
import com.thiaagodev.convidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    suspend fun insert(guest: GuestModel) : Long

    @Update
    suspend fun update(guest: GuestModel) : Int

    @Delete
    suspend fun delete(guest: GuestModel?)

    @Query("SELECT * FROM Guest WHERE id = :id")
    suspend fun get(id: Int?): GuestModel?

    @Query("SELECT * FROM Guest")
    suspend fun getAll(): List<GuestModel>

    @Query("SELECT * FROM Guest WHERE presence = :presence")
    suspend fun getByPresence(presence: Int): List<GuestModel>
}