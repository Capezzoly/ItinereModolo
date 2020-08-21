package com.modolo.itineremodolo.firststart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.modolo.itineremodolo.data.user.User
import com.modolo.itineremodolo.data.user.UserRepository

class LoginViewModel(val app: Application) : AndroidViewModel(app) {
    private val db = UserRepository(app)
    var userList = MutableLiveData<List<User>>()

    init {
        userList = db.userData
    }

    fun insertUser(user: User) {
        db.insertUser(user)
    }

    fun login(mail: String) {
        db.login(mail)
    }

    fun deleteUser(user: User) {
        db.deleteUser(user)
    }

    fun updateUser(user: User){
        db.updateUser(user)
    }

    fun logout() {
        db.logout()
    }

}