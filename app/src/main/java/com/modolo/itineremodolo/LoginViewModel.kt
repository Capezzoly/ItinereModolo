package com.modolo.itineremodolo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.modolo.itineremodolo.data.user.User
import com.modolo.itineremodolo.data.user.UserRepository

class LoginViewModel (val app:Application): AndroidViewModel(app){
    private val db = UserRepository(app)
    var userList = MutableLiveData<List<User>>()
    var session = false
    init{
        //session()
        userList = db.userData
    }

    fun insertUser(user: User){
        db.insertUser(user)
    }

    fun getUser(mail: String): User?{
        db.getUser(mail)
        return db.userFound.value
    }

    fun login(mail: String){
        db.login(mail)
    }

    fun deleteUser(user: User){
        db.deleteUser(user)
    }

    fun session(): Boolean{
        return session
    }

    fun logout(){
        db.logout()
    }

}