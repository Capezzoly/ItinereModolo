package com.modolo.itineremodolo

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtBirthDate.setOnClickListener{
            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    txtBirthDate.text = "$dayOfMonth/${month + 1}/$year"
                },
                1970,
                1,
                1
            ).show()
        }

        btnSignup.setOnClickListener {
            /*
            TODO CHECK PRESENZA MAIL IN DB
            if(email già in db){
            txtSignupEmail.background= ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
            Toast.makeText(this , "ACCOUNT ESISTENTE", Toast.LENGTH_SHORT).show()
            }
            else if(txtSignupPsw2!=txtSignupPsw1){
            txtSignupPsw2.background= ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
            txtSignupPsw1.background= ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
            Toast.makeText(this , "LE PASSWORD NON COINCIDONO", Toast.LENGTH_SHORT).show()
            }
            else{
             */
            val intent = Intent(baseContext, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        }

        txtNoSnitch.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("http://www.simcareer.org/")
            startActivity(openURL)
        }
    }
}
