package com.modolo.itineremodolo.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val mail: String,
    val sess: Int,
    val pass: String,
    val name: String,
    val surn: String,
    val data: String,
    val numb: String,
    val fcir: String,
    val hcir: String,
    val fcar: String,
    val caid: String,
    val cama: String,
    val cate: String
): Serializable