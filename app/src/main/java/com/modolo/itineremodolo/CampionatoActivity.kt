package com.modolo.itineremodolo

 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.campionati.*
import com.modolo.itineremodolo.classifiche.CampionatiClassifiche
import com.modolo.itineremodolo.classifiche.ClassificaPilotiAdapter
import com.modolo.itineremodolo.classifiche.ClassificaTeamAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_campionato.*

class CampionatoActivity : AppCompatActivity() {
    val listTypeClassifiche = Types.newParameterizedType(
        List::class.java, CampionatiClassifiche::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campionato)

        val campionato = intent.getSerializableExtra("campionato") as? Campionati
        txtTitleCampionato.text = campionato?.nome.toString()

        val recycleCalendario = findViewById<RecyclerView>(R.id.viewRaces)
        val adapterCalendario = CalendarioAdapter(this, campionato?.calendario)
        recycleCalendario.adapter = adapterCalendario

        val recyclePiloti = findViewById<RecyclerView>(R.id.viewPiloti)
        val adapterPiloti = PilotiAdapter(this, campionato?.pilotiiscritti)
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
        for(i in 1..16)
        {
            var nome_immagine = "GTE"
            if(campionato?.id?.toInt()?.rem(2)==0)
                nome_immagine+="_"
            nome_immagine+=i.toString()+".jpg"
            listaImmagini.add(nome_immagine)
        }
        val recycleGallery = findViewById<RecyclerView>(R.id.viewGallery)
        val adapterGallery= GalleryAdapter(this, listaImmagini)
        recycleGallery.adapter=adapterGallery

    }
}
