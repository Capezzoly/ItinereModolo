package com.modolo.itineremodolo

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.lang.reflect.ParameterizedType

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtBirthDate.setOnClickListener {
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
            txtSignupPsw1.background =
                ResourcesCompat.getDrawable(this.resources, R.drawable.little_box, null)
            txtSignupPsw2.background =
                ResourcesCompat.getDrawable(this.resources, R.drawable.little_box, null)
            txtSignupEmail.background =
                ResourcesCompat.getDrawable(this.resources, R.drawable.little_box, null)


            var ok = true
            if (txtSignupEmail.text.toString() == "") {
                ok = false
                txtSignupEmail.background = ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.little_box_error,
                    null
                )
            }
            if (txtSignupPsw2.text.toString() != txtSignupPsw1.text.toString() || txtSignupPsw1.text.toString() == "") {
                txtSignupPsw2.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                txtSignupPsw1.background =
                    ResourcesCompat.getDrawable(this.resources, R.drawable.little_box_error, null)
                if (txtSignupPsw1.text.toString() != "")
                    Toast.makeText(this, "LE PASSWORD NON COINCIDONO", Toast.LENGTH_SHORT).show()
                ok = false
            }
            if (ok) {
                val intent = Intent(baseContext, MainActivity::class.java)
                this.finish()
                startActivity(intent)
            } else {
                scrollView.smoothScrollTo(0, 0);
            }
        }

        txtNoSnitch.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("http://www.simcareer.org/")
            startActivity(openURL)
        }
    }

}
