package com.modolo.itineremodolo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.userList.observe(this, Observer { it ->
            it.forEach {
                if (it.sess == 1) {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    this.finish()
                    startActivity(intent)
                    return@forEach
                }
            }
        })


        btnLogin.setOnClickListener {
            txtLoginEmail.background =
                ResourcesCompat.getDrawable(this.resources, R.drawable.little_box, null)
            txtLoginPsw.background = txtLoginEmail.background
            var login = false
            loginViewModel.userList.observe(this, Observer {
                it.forEach {
                    if (it.mail == txtLoginEmail.text.toString()) {
                        if (it.pass == txtLoginPsw.text.toString()) {
                            login = true
                            loginViewModel.login(it.mail)
                            val intent = Intent(baseContext, MainActivity::class.java)
                            startActivity(intent)
                            this.finish()
                            return@forEach
                        } else {
                            login = true
                            Toast.makeText(this, "PASSWORD ERRATA", Toast.LENGTH_SHORT).show()
                            txtLoginPsw.background =
                                ResourcesCompat.getDrawable(
                                    this.resources,
                                    R.drawable.little_box_error,
                                    null
                                )
                            return@forEach
                        }
                    }
                }
            })
            if (!login) {
                Toast.makeText(this, "PROFILO NON TROVATO", Toast.LENGTH_SHORT).show()
                txtLoginEmail.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                txtLoginPsw.background = txtLoginEmail.background
            }
        }

        newAccount2.setOnClickListener {
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }

        txtForgotPsw.setOnClickListener {
            Toast.makeText(this, "PASSWORD DIMENTICATA", Toast.LENGTH_SHORT).show()
        }

        imgSocial.setOnClickListener {
            Toast.makeText(this, "LOGIN CON SOCIAL", Toast.LENGTH_SHORT).show()
        }
    }
}
