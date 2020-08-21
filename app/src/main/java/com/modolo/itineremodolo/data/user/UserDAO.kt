package com.modolo.itineremodolo.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Query("SELECT * FROM users WHERE mail=:mail")
    suspend fun getUser(mail: String): User

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    //Salviamo l'utente che ha fatto login
    @Query("UPDATE users SET sess = 1 WHERE mail=:mail")
    suspend fun login(mail: String)

    @Query("UPDATE users SET sess = 0 WHERE sess=1")
    suspend fun logout()

    @Query("UPDATE users SET name =:name, surn=:surn, data=:data, numb=:numb, fcir=:fcir, hcir=:hcir, fcar=:fcar WHERE sess=1")
    suspend fun updateUser(name: String, surn: String, data: String, numb: String, fcir: String, hcir: String, fcar: String)


}