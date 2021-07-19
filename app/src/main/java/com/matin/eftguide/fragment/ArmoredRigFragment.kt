package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.R
import com.matin.eftguide.RigActivity
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain


class ArmoredRigFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armored_rig, container, false)

        val list = arrayListOf<RecyclerMain>()

        val armors = mutableListOf(R.drawable.a_6b5_16, R.drawable.a_6b3tm, R.drawable.a_6b5_15, R.drawable.m2, R.drawable.mk4a_assault, R.drawable.m1, R.drawable.avs, R.drawable.a18, R.drawable.tv_110, R.drawable.tactec, R.drawable.mk4a_protection, R.drawable.aacpc, R.drawable.avs_mbav)
        val names = mutableListOf("6B5-16", "6B3TM", "6B5-15", "M2", "MK4A (Assault)", "M1", "AVS", "A18", "TV-110", "Tactec", "MK4A (Protection)", "AACPC", "MBAV (Tagilla Edition)")

        for(i in armors.indices){
            list.add(RecyclerMain(armors[i], names[i], context!!, RigActivity::class.java, "h_${names[i]}||"))
        }
        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_armored_rig_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }


}