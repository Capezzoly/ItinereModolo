package com.modolo.itineremodolo.ui.campionati

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.modolo.itineremodolo.*
import com.modolo.itineremodolo.championships.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


class CampionatiFragment : Fragment(), ChampAdapter.ChampListener,
    AdapterView.OnItemSelectedListener {
    var championships = mutableListOf<Champ>()

    companion object {
        fun newInstance() =
            CampionatiFragment()
    }

    private lateinit var viewModel: CampionatiViewModel
    val listTypeCampionati = Types.newParameterizedType(
        List::class.java, Campionati::class.java
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.campionati_fragment, container, false)
        createDummyData()
        val champs = view.findViewById<RecyclerView>(R.id.championships)
        val adapterChamps = ChampAdapter(requireContext(), championships, this)
        champs.adapter = adapterChamps


        val text = FileHelper.getData(requireContext(), "campionati.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Campionati>> = moshi.adapter(listTypeCampionati)
        val campionati: List<Campionati>? = adapter.fromJson(text)
        Log.i("tazza", campionati.toString())

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CampionatiViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun createDummyData() {
        championships.add(Champ(0, "CAMPIONATO 0", "todo", 4, 6, true))
        championships.add(Champ(1, "CAMPIONATO 1", "todo", 8, 1, false))
        championships.add(Champ(2, "CAMPIONATO 2", "todo", 4, 6, true))
        championships.add(Champ(3, "CAMPIONATO 3", "todo", 8, 1, false))

    }

    override fun onChampListener(champ: Champ, position: Int) {
        val intent = Intent(requireContext(), ChampionshipActivity::class.java)
        startActivity(intent)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}
