package com.example.suitmediaapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediaapp.R
import com.example.suitmediaapp.adapter.ListEventAdapter
import com.example.suitmediaapp.adapter.ListGuestAdapter
import com.example.suitmediaapp.data.model.Event
import com.example.suitmediaapp.data.model.EventData
import com.example.suitmediaapp.databinding.FragmentMapViewBinding

class MapViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_view, container, false)

    }


}