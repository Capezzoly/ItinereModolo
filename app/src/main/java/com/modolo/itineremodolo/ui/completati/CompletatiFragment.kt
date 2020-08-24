package com.modolo.itineremodolo.ui.completati

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.modolo.itineremodolo.R
import com.modolo.itineremodolo.campionati.Campionati
import com.modolo.itineremodolo.campionati.CampionatiAdapter
import com.modolo.itineremodolo.CampionatoActivity
import com.modolo.itineremodolo.FileHelper
import com.modolo.itineremodolo.ui.campionati.CampionatiViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.text.SimpleDateFormat

class CompletatiFragment : Fragment(), CampionatiAdapter.CampionatiListener,
    AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: CampionatiViewModel
    val listTypeCampionati = Types.newParameterizedType(
        List::class.java, Campionati::class.java
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val text = FileHelper.getData(requireContext(), "campionati.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Campionati>> = moshi.adapter(listTypeCampionati)
        val campionati: List<Campionati>? = adapter.fromJson(text)

        var campionatiTODO = mutableListOf<Campionati>()
        var campionatiDONE = mutableListOf<Campionati>()

        campionati?.forEach{
            val dataParsed = SimpleDateFormat("dd-MM-yyyy").parse(it.calendario[it.calendario.size - 1].data)
            if(dataParsed.time > System.currentTimeMillis())
                campionatiTODO.add(it)
            else
                campionatiDONE.add(it)
        }

        val view = inflater.inflate(R.layout.campionati_fragment, container, false)
        val campionatiRecycle = view.findViewById<RecyclerView>(R.id.recycleCampionati)
        val adapterChamps = CampionatiAdapter(requireContext(), campionatiDONE, this)
        campionatiRecycle.adapter = adapterChamps
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CampionatiViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCampionatiListener(campionato: Campionati, position: Int) {
        val intent = Intent(requireContext(), CampionatoActivity::class.java)
        intent.putExtra("campionato", campionato)
        startActivity(intent)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}