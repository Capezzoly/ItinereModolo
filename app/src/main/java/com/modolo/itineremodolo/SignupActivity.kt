package com.modolo.itineremodolo

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.io.File
import java.io.FileInputStream

class SignupActivity : AppCompatActivity() {

    val listTypeUsers = Types.newParameterizedType(
        List::class.java, Users::class.java
    )

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
        var listaUtenti = getUsers()
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
            if (ok) {
                listaUtenti?.forEach {
                    if (it.mail == txtSignupEmail.text.toString()) {
                        txtSignupEmail.background = ResourcesCompat.getDrawable(
                            this.resources,
                            R.drawable.little_box_error,
                            null
                        )
                        Toast.makeText(this, "ACCOUNT ESISTENTE", Toast.LENGTH_SHORT).show()
                        ok = false
                    }
                }
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
                /*val newuser = Users(
                    txtSignupEmail.text.toString(),
                    txtSignupPsw1.text.toString(),
                    txtName.text.toString(),
                    txtSurname.text.toString(),
                    txtBirthDate.text.toString(),
                    favNumber.text.toString(),
                    favCircuit.text.toString(),
                    hateCircuit.text.toString(),
                    favCar.text.toString()
                )*/
                val user = txtSignupEmail.text.toString() +"\n"+
                        txtSignupPsw1.text.toString() +"\n"+
                        txtName.text.toString() +"\n"+
                        txtSurname.text.toString() +"\n"+
                        txtBirthDate.text.toString() +"\n"+
                        favNumber.text.toString() +"\n"+
                        favCircuit.text.toString() +"\n"+
                        hateCircuit.text.toString() +"\n"+
                        favCar.text.toString()
                val path = this.filesDir
                val sessioneDirectory = File(path, "session")
                sessioneDirectory.mkdirs()
                val file = File(sessioneDirectory, "session.txt")
                file.appendText(user)
                //val userToParse = FileInputStream(file).bufferedReader().use { it.readText() }


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

    fun getUsers(): List<Users>? {
        val text = FileHelper.getData(this, "users.json")
        return parserUser(text)
    }

    fun parserUser(text: String): List<Users>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Users>> = moshi.adapter(listTypeUsers)
        return adapter.fromJson(text)
    }
}
