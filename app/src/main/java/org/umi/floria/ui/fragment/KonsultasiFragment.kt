package org.umi.floria.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.umi.floria.R
import org.umi.floria.ui.adapter.KonsultasiAdapter

class KonsultasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_konsultasi, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViews)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dataSet = mutableListOf<Any>() // Isi dengan data card sesuai kebutuhan
        repeat(10) {
            dataSet.add(Unit) // Tambahkan sesuai jumlah item yang diinginkan
        }

        val adapter = KonsultasiAdapter(dataSet)
        recyclerView.adapter = adapter

        return view
    }
}

