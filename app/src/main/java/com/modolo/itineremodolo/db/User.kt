package com.modolo.itineremodolo.db

import androidx.room.*
import java.util.*

@Entity(tableName="users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val ID: Int, //metto '0' per autogenerarlo
    val mail: String,
    val pswd: String,
    val nome: String,
    val cogn: String,
    val ddin: Date,
    var gara: Int,
    val fcir: String,
    val hcir: String,
    val fcar: String
    )