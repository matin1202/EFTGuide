package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.R
import com.matin.eftguide.adapter.hideout.HideoutAdapter
import com.matin.eftguide.adapter.hideout.RecyclerHideout

class DealerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dealer, container, false)

        val list = mutableListOf<RecyclerHideout>()

        list.add(
            RecyclerHideout(
                R.drawable.prapor ,
                getString(R.string.prapor),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.therapist ,
                getString(R.string.therapist),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.fence ,
                getString(R.string.fence),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.skier ,
                getString(R.string.skier),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.peacekeeper ,
                getString(R.string.peacekeeper),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.mechanic ,
                getString(R.string.mechanic),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.ragman ,
                getString(R.string.ragman),
                context!!
            )
        )
        list.add(
            RecyclerHideout(
                R.drawable.jaeger ,
                getString(R.string.jaeger),
                context!!
            )
        )


        val adapter = HideoutAdapter(list as ArrayList<RecyclerHideout>)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rl_dealer_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

}