package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.MapActivity
import com.matin.eftguide.R
import com.matin.eftguide.adapter.ammo.AmmoAdapter
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain

class MapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val list = ArrayList<RecyclerMain>()

        list.add(
            RecyclerMain(
                R.drawable.interchange,
                getString(R.string.interchange),
                context!!,
                MapActivity::class.java,
                "interchange"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.customs,
                getString(R.string.customs),
                context!!,
                MapActivity::class.java,
                "customs"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.woods,
                getString(R.string.woods),
                context!!,
                MapActivity::class.java,
                "woods"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.factory,
                getString(R.string.factory),
                context!!,
                MapActivity::class.java,
                "factory"
            )
        )

        list.add(
                RecyclerMain(
                    R.drawable.the_lab,
                    getString(R.string.the_lab),
                    context!!,
                    MapActivity::class.java,
                    "the_lab"
                )
                )

        list.add(
            RecyclerMain(
                R.drawable.reserve,
                getString(R.string.reserve),
                context!!,
                MapActivity::class.java,
                "reserve"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.shoreline,
                getString(R.string.shoreline),
                context!!,
                MapActivity::class.java,
                "shoreline"
            )
        )

        val adapter = MainAdapter(list)

        val recyclerView: RecyclerView = view.findViewById(R.id.rl_map_menu)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}