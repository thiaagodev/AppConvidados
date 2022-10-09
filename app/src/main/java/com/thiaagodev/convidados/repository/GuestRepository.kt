package com.thiaagodev.convidados.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.thiaagodev.convidados.constants.DataBaseConstants
import com.thiaagodev.convidados.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)

    // Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {

            if (!Companion::repository.isInitialized) {
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

            values.put(DataBaseConstants.Guest.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)


            db.insert(DataBaseConstants.Guest.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {

        return try {
            val db = guestDataBase.writableDatabase

            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0
            values.put(DataBaseConstants.Guest.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)

            val selection = "${DataBaseConstants.Guest.COLUMNS.ID} = ?"
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.Guest.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int?): Boolean {

        return try {
            val db = guestDataBase.writableDatabase

            val selection = "${DataBaseConstants.Guest.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.Guest.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {

        val guestList = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.Guest.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                    guestList.add(guest)
                }
            }

            cursor.close()

            return guestList

        } catch (e: Exception) {
            return guestList
        }
    }

    fun get(id: Int?): GuestModel? {

        var guest: GuestModel? = null

        try {
            val db = guestDataBase.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )

            val selection = "${DataBaseConstants.Guest.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.Guest.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    guest = GuestModel(id, name, presence == 1)
                }
            }

            cursor.close()

            return guest

        } catch (e: Exception) {
            return guest
        }
    }

    fun getByPresence(presenceFilter: Int): List<GuestModel> {

        val guestList = mutableListOf<GuestModel>()

        try {
            val db = guestDataBase.readableDatabase

            val columns = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID,
                DataBaseConstants.Guest.COLUMNS.NAME,
                DataBaseConstants.Guest.COLUMNS.PRESENCE
            )

            val selection = "${DataBaseConstants.Guest.COLUMNS.PRESENCE} = ?"
            val args = arrayOf(presenceFilter.toString())

            val cursor = db.query(
                DataBaseConstants.Guest.TABLE_NAME,
                columns,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    val guest = GuestModel(id, name, presence == 1)
                    guestList.add(guest)
                }
            }

            cursor.close()

            return guestList

        } catch (e: Exception) {
            return guestList
        }
    }

}