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


class CampionatiFragment : Fragment(), CampionatiAdapter.CampionatiListener,
    AdapterView.OnItemSelectedListener {
    //var campionati = mutableListOf<Campionati>()

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
        val text = FileHelper.getData(requireContext(), "campionati.json")
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Campionati>> = moshi.adapter(listTypeCampionati)
        val campionati: List<Campionati>? = adapter.fromJson(text)
        val view = inflater.inflate(R.layout.campionati_fragment, container, false)
        val campionatiRecycle = view.findViewById<RecyclerView>(R.id.recycleCampionati)
        val adapterChamps = CampionatiAdapter(requireContext(), campionati, this)
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
        startActivity(intent)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

}
