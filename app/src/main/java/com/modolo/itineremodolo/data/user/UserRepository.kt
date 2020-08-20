package com.modolo.itineremodolo.data.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(val app: Application) {
    var userData = MutableLiveData<List<User>>()
    var userFound = MutableLiveData<User>()
    var session = MutableLiveData<Boolean>()
    private val userDAO = UserDatabase.getDatabase(app)
        .userDAO()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data: List<User>? = userDAO.getAll()
            if (data.isNullOrEmpty()) {
                //caso in cui il db sia vuoto
                Log.i("conan", "if")
                dummyData()
            } else {
                //caso in cui il db abbia delle entry
                Log.i("conan", "else")
                userData.postValue(data)
                withContext(Dispatchers.Main) {
                    //do not delete
                }
            }
        }
    }

    fun insertUser(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.insertUser(user)
        }
    }
    fun session() {
        CoroutineScope(Dispatchers.IO).launch {
            var s = false
            if (userDAO.session() >= 1)
                s = true
            Log.i("debug2", "result: $s")
            session.postValue(s)
        }
    }

    fun logout(){
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.logout()
        }
    }

    fun login(mail: String){
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.login(mail)
        }
    }

    fun deleteUser(user: User){
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.deleteUser(user)
        }
    }

    fun getUser(mail: String){
        CoroutineScope(Dispatchers.IO).launch {
            userFound.postValue(userDAO.getUser(mail))
        }
    }

    fun dummyData() {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.insertUser(
                User(
                    "miraxh@gmail.com",
                    0,
                    "pleb",
                    "Miraxh",
                    "Tereziu",
                    "28/02/1998",
                    "45",
                    "Circuito Preferito",
                    "Circuito Odiato",
                    "Maggiolino"
                )
            )
            userDAO.insertUser(
                User(
                    "davide.modolo@studenti.unitn.it",
                    0,
                    "admin",
                    "Davide",
                    "Modolo",
                    "14/06/1998",
                    "69",
                    "Circuito Preferito",
                    "Circuito Odiato",
                    "Batmobile"
                )
            )
            userDAO.insertUser(
                User(
                    "molly@gmail.com",
                    0,
                    "pleb2",
                    "Luca",
                    "Mosetti",
                    "17/10/1998",
                    "420",
                    "Circuito Preferito",
                    "Circuito Odiato",
                    "Hotweels"
                )
            )
        }
    }
}