package com.thiaagodev.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int? = null

    @ColumnInfo
    val name: String = ""

    @ColumnInfo
    val presence: Boolean = false
}

