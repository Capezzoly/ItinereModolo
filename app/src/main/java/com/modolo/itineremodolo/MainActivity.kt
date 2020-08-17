package com.modolo.itineremodolo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_campionati -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.campionatiFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_completati -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.completatiFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(navigationListener)

        fab.setOnClickListener {
            /*
            passare all'activity registrati "1 per dire che c'è un login attivo"
            e poi nel caricare il registrati if(login) blocca certi campi e scegli il testo
             */
            val intent = Intent(baseContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }


}
