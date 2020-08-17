package com.modolo.itineremodolo.ui.completati

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.modolo.itineremodolo.R

class CompletatiFragment : Fragment() {

    companion object {
        fun newInstance() = CompletatiFragment()
    }

    private lateinit var viewModel: CompletatiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.completati_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CompletatiViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
