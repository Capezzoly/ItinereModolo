package com.modolo.itineremodolo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        imgSocial.setOnClickListener {
            Toast.makeText(this , "LOGIN CON SOCIAL NON DISPONIBILE", Toast.LENGTH_SHORT).show()

        }

        btnLogin.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        }

        newAccount2.setOnClickListener {
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
