package com.modolo.itineremodolo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val listTypeUsers = Types.newParameterizedType(
        List::class.java, Users::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        imgSocial.setOnClickListener {
            Toast.makeText(this, "LOGIN CON SOCIAL NON DISPONIBILE", Toast.LENGTH_SHORT).show()

        }
        var listaUtenti = getUsers()

        btnLogin.setOnClickListener {
            var login = false
            listaUtenti?.forEach {
                if (it.mail == txtLoginEmail.text.toString()) {
                    if (it.psw == (txtLoginPsw.text.toString())) {//LOGIN CORRETTO
                        login = true
                        val intent = Intent(baseContext, MainActivity::class.java)
                        this.finish()
                        startActivity(intent)
                    }
                }
            }
            if(!login) {
                txtLoginEmail.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                txtLoginPsw.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                Toast.makeText(this, "PROFILO NON TROVATO, RIPROVA", Toast.LENGTH_SHORT).show()
            }
        }

        txtForgotPsw.setOnClickListener {
            listaUtenti?.forEach {
                if (it.mail == txtLoginEmail.text.toString()) {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        this.finish()
                        startActivity(intent)
                }
            }
        }

        newAccount2.setOnClickListener {
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    fun getUsers(): List<Users>? {
        val text = FileHelper.getData(this, "users.json")
        return parserUser(text)
    }

    fun parserUser(text: String) : List<Users>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Users>> = moshi.adapter(listTypeUsers)
        return adapter.fromJson(text)
    }
}
