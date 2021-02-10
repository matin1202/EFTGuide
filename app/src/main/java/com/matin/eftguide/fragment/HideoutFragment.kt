package com.matin.eftguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.R
import com.matin.eftguide.adapter.hideout.HideoutAdapter
import com.matin.eftguide.adapter.hideout.RecyclerHideout


class HideoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hideout, container, false)

        val list = mutableListOf<RecyclerHideout>()

        list.add(
            RecyclerHideout(
                R.drawable.shootingrange,
                "ShootingRange(사격장)",
                context!!
            )
        )

        val adapter = HideoutAdapter(list as ArrayList<RecyclerHideout>)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_hideout_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }
}