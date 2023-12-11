package org.umi.floria.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.umi.floria.R
import org.umi.floria.models.GridItem
import org.umi.floria.ui.adapter.KonsultasiAdapter

class KonsultasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_konsultasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnStartNow: Button = view.findViewById(R.id.bottomBarButton)
        val behavior = BottomSheetBehavior.from(view.findViewById(R.id.standard_bottom_sheet))
        btnStartNow.setOnClickListener {
            btnStartNow.alpha = 0.1f
            btnStartNow.isEnabled = false
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViews)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val items = listOf(
            GridItem("Item 1", "Description 1", "Fee 1"),
            GridItem("Item 2", "Description 2", "Fee 2"),
            GridItem("Item 1", "Description 1", "Fee 1"),
            GridItem("Item 2", "Description 2", "Fee 2"),
            GridItem("Item 1", "Description 1", "Fee 1"),

            // Add more items as needed
        )

        val adapter = KonsultasiAdapter(items)
        recyclerView.adapter = adapter
    }
}

