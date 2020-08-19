package com.modolo.itineremodolo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File
import java.io.FileWriter


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //*********************************************************
        val jsonFileString = getJsonDataFromAsset(applicationContext, "users.json")
        Log.i("data", jsonFileString)

        val gson = Gson()
        val listUserType = object : TypeToken<List<User>>() {}.type

        val users: MutableList<User> = gson.fromJson(jsonFileString, listUserType)
        users.forEachIndexed { idx, user -> Log.i("data", "> Item $idx:\n$user") }

        val newuser = User(
            "Tereziu",
            "29/02/1998",
            "Maggiolino",
            "Tondo",
            "Quadrato",
            "miraxh@gmail.com",
            "Miraxh",
            "45",
            "userscarso"
        )
        users.add(newuser)
        users?.forEach{
            Log.i("data", it.toString())
        }

        var gson2 = Gson()
        var jsonString:String = gson2.toJson(newuser)
        val file= File("users.json")
        file.writeText(jsonString)

        //*******************************************************

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        imgSocial.setOnClickListener {
            Toast.makeText(this, "LOGIN CON SOCIAL NON DISPONIBILE", Toast.LENGTH_SHORT).show()

        }

        btnLogin.setOnClickListener {
            var login = false
            if(!login) {
                txtLoginEmail.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                txtLoginPsw.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                Toast.makeText(this, "PROFILO NON TROVATO, RIPROVA", Toast.LENGTH_SHORT).show()
            }
        }

        txtForgotPsw.setOnClickListener {
            var ok = false
            if(!ok){
                txtLoginEmail.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                Toast.makeText(this, "PROFILO NON TROVATO, RIPROVA", Toast.LENGTH_SHORT).show()
            }
        }
        newAccount2.setOnClickListener {
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
