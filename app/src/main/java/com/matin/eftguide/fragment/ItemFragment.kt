package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.MenuActivity
import com.matin.eftguide.R
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain

class ItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        val list = ArrayList<RecyclerMain>()

        list.add(
            RecyclerMain(
                R.drawable.healkit,
                getString(R.string.heal),
                context!!,
                MenuActivity::class.java,
                "heal"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.energy,
                getString(R.string.provision),
                context!!,
                MenuActivity::class.java,
                "provision"
            )
        )

        /*list.add(
            RecyclerMain(
                R.drawable.secure_container,
                getString(R.string.secure_container),
                context!!,
                MenuActivity::class.java,
                "container"
            )
        )*/

        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_item_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}