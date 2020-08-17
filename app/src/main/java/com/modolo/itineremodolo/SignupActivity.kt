package com.modolo.itineremodolo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignup.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        }
    }
}
