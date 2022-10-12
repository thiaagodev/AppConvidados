package com.thiaagodev.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int? = null

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var presence: Boolean = false
}

