package com.modolo.itineremodolo

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.campionati.*
import com.modolo.itineremodolo.classifiche.CampionatiClassifiche
import com.modolo.itineremodolo.classifiche.ClassificaPilotiAdapter
import com.modolo.itineremodolo.classifiche.ClassificaTeamAdapter
import com.modolo.itineremodolo.firststart.LoginViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_campionato.*
import java.text.SimpleDateFormat

class CampionatoActivity : AppCompatActivity() {
    val listTypeClassifiche = Types.newParameterizedType(
        List::class.java, CampionatiClassifiche::class.java
    )

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campionato)

        val campionato = intent.getSerializableExtra("campionato") as? Campionati
        txtTitleCampionato.text = campionato?.nome.toString()

        var caid = listOf("")
        var cama = listOf("")
        var cate = listOf("")
        var nome = ""
        var cogn = ""
        val idCampionato = campionato?.id?.toInt()!!
        val recyclePiloti = findViewById<RecyclerView>(R.id.viewPiloti)
        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.userList.observe(this, Observer { it ->
            it.forEach {
                if (it.sess == 1) {
                    caid = it.caid.split(",").toList()
                    cama = it.cama.split(",").toList()
                    cate = it.cate.split(",").toList()
                    nome = it.name
                    cogn = it.surn
                    caid.forEachIndexed { index, it ->
                        if (caid[index] == idCampionato.toString()) {
                            campionato.pilotiiscritti.add(
                                Pilotiiscritti(
                                    nome+" "+cogn,
                                    cate[index],
                                    cama[index]
                                )
                            )
                            btnIscriviti.text = "DISISCRIVITI"
                        }
                    }
                    val adapterPiloti = PilotiAdapter(this, campionato.pilotiiscritti)
                    recyclePiloti.adapter = adapterPiloti
                    return@forEach
                }
            }
        })

        val recycleCalendario = findViewById<RecyclerView>(R.id.viewRaces)
        val adapterCalendario = CalendarioAdapter(this, campionato?.calendario)
        recycleCalendario.adapter = adapterCalendario


        var adapterPiloti = PilotiAdapter(this, campionato?.pilotiiscritti)
        recyclePiloti.adapter = adapterPiloti

        val recycleImpostazioni = findViewById<RecyclerView>(R.id.viewSettings)
        val adapterImpostazioni = ImpostazioniAdapter(this, campionato?.impostazioni)
        recycleImpostazioni.adapter = adapterImpostazioni

        val text = FileHelper.getData(this, "classifiche.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<CampionatiClassifiche>> = moshi.adapter(listTypeClassifiche)
        val classifiche: List<CampionatiClassifiche>? = adapter.fromJson(text)
        classifiche?.forEach {
            if (it.id == campionato?.id) {
                val recycleClassificaPiloti = findViewById<RecyclerView>(R.id.viewRankingP)
                val adapterClassificaPiloti = ClassificaPilotiAdapter(this, it.classificapiloti)
                recycleClassificaPiloti.adapter = adapterClassificaPiloti

                val recycleClassificaTeam = findViewById<RecyclerView>(R.id.viewRankingS)
                val adapterClassificaTeam = ClassificaTeamAdapter(this, it.classificateam)
                recycleClassificaTeam.adapter = adapterClassificaTeam

            }
        }


        var listaImmagini = mutableListOf<String>()
        for (i in idCampionato + 1..10 + idCampionato + 1) {
            var nome_immagine = "GTE"
            if (idCampionato.rem(2) == 0)
                nome_immagine += "_"
            nome_immagine += "$i.jpg"
            listaImmagini.add(nome_immagine)
        }
        val recycleGallery = findViewById<RecyclerView>(R.id.viewGallery)
        val adapterGallery = GalleryAdapter(this, listaImmagini)
        recycleGallery.adapter = adapterGallery

        btnIscriviti.setOnClickListener {
            if (btnIscriviti.text.toString() == "ISCRIVITI") {
                val dialog = Dialog(this)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.subscribe)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()

                val btnSave = dialog.findViewById<Button>(R.id.btnConfirm)
                val yourTeam = dialog.findViewById<EditText>(R.id.iscrTeam)
                val spinnerConsole = dialog.findViewById<Spinner>(R.id.spinnerCars)
                val adp = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    campionato.listaauto.split(",").toTypedArray()
                )
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerConsole.adapter = adp

                btnSave.setOnClickListener {
                    if (yourTeam.text.toString() == "")
                        yourTeam.error = "Team is required!"
                    else {
                        loginViewModel.userList.observe(this, Observer { it ->
                            it.forEach {
                                if (it.sess == 1) {
                                    loginViewModel.updateSub(
                                        it.caid + "," + idCampionato.toString(),
                                        it.cama + "," + spinnerConsole.selectedItem.toString(),
                                        it.cate + "," + yourTeam.text.toString()
                                    )
                                    campionato.pilotiiscritti.add(
                                        Pilotiiscritti(
                                            it.name + " " + it.surn,
                                            yourTeam.text.toString(),
                                            spinnerConsole.selectedItem.toString()
                                        )
                                    )
                                    val adapterPiloti =
                                        PilotiAdapter(this, campionato.pilotiiscritti)
                                    recyclePiloti.adapter = adapterPiloti
                                    btnIscriviti.text = "DISISCRIVITI"
                                    dialog.dismiss()
                                    return@forEach
                                }
                            }
                        })
                    }
                }
                dialog.show()
            } else {
                loginViewModel.userList.observe(this, Observer { it ->
                    it.forEach {
                        if (it.sess == 1) {//RIMUOVERE ISCRIZIONE
                            var listapiloti = campionato.pilotiiscritti
                            listapiloti.removeLast()
                            adapterPiloti = PilotiAdapter(this, listapiloti)
                            recyclePiloti.adapter = adapterPiloti
                            btnIscriviti.text = "ISCRIVITI"
                            return@forEach
                        }
                    }
                })
            }
        }

        val dataParsed =
            SimpleDateFormat("dd-MM-yyyy").parse(campionato.calendario[campionato.calendario.size - 1].data)
        if (dataParsed.time < System.currentTimeMillis())
            btnIscriviti.isVisible = false

    }
}

private fun <E> List<E>.remove(toString: E) {

}
