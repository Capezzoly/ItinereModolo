package com.modolo.itineremodolo.data.user

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val app: Application) {
    var userData = MutableLiveData<List<User>>()
    var userFound = MutableLiveData<User>()
    private val userDAO = UserDatabase.getDatabase(app)
        .userDAO()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data: List<User>? = userDAO.getAll()
            if (data.isNullOrEmpty()) {
                //caso in cui il db sia vuoto
                userDAO.insertUser(
                    User(
                        "mario.rossi@gmail.com",
                        0,
                        "test",
                        "Mario",
                        "Rossi",
                        "28/02/1998",
                        "45",
                        "Circuito Preferito",
                        "Circuito Odiato",
                        "Maggiolino",
                        "",
                        "",
                        ""
                    )
                )
                userDAO.insertUser(
                    User(
                        "davide.modolo@gmail.com",
                        0,
                        "admin",
                        "Davide",
                        "Modolo",
                        "14/06/1998",
                        "69",
                        "Circuito Preferito",
                        "Circuito Odiato",
                        "Batmobile",
                        "1",
                        "Mercedes Classe A",
                        "Team Picko"
                    )
                )
                userDAO.insertUser(
                    User(
                        "stefano.verdi@gmail.com",
                        0,
                        "test2",
                        "Stefano",
                        "Verdi",
                        "17/10/1998",
                        "420",
                        "Circuito Preferito",
                        "Circuito Odiato",
                        "Hotweels",
                        "",
                        "",
                        ""
                    )
                )
                val data2: List<User>? = userDAO.getAll()
                userData.postValue(data2)
            } else {
                //caso in cui il db abbia delle entry
                userData.postValue(data)
            }
        }
    }

    fun insertUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.insertUser(user)
        }
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.logout()
        }
    }

    fun login(mail: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.login(mail)
        }
    }

    fun deleteUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.deleteUser(user)
        }
    }

    fun getUser(mail: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userFound.postValue(userDAO.getUser(mail))
        }
    }

    fun updateSub(caid: String, cama: String, cate:String){
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.updateSubscription(caid, cama, cate)
        }
    }

    fun updateUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDAO.updateUser(
                user.name,
                user.surn,
                user.data,
                user.numb,
                user.fcir,
                user.hcir,
                user.fcar
            )
            val data:List<User>?=userDAO.getAll()
            userData.postValue(data)
        }
    }

}