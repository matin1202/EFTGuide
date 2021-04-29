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

class ChestRigFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chest_rig, container, false)

        val list = arrayListOf<RecyclerMain>()

        val armors = mutableListOf(R.drawable.a_scav_vest, R.drawable.a_security_vest, R.drawable.a_idea, R.drawable.a_bank_robber, R.drawable.a_micro_rig, R.drawable.a_wartech, R.drawable.a_csa, R.drawable.a_6sh112, R.drawable.a_tarzan, R.drawable.a_d3crx, R.drawable.a_triton, R.drawable.a_commando, R.drawable.a_thunderbolt, R.drawable.a_bss_mk1, R.drawable.a_umka, R.drawable.a_lbt_1961, R.drawable.a_blackrock, R.drawable.a_mk3, R.drawable.a_alpha, R.drawable.a_azimut, R.drawable.a_mppv, R.drawable.a_belt)
        val names = mutableListOf("scav Vest", "Security Vest", "IDEA chest rig", "Bank Robber", "Micro Rig", "Wartech Gear Rig", "CSA Chest rig", "6SH112", "Tarzan", "D3CRX", "Triton", "Commando", "Thunderbolt", "BSS Mk1", "UMKA", "LBT 1961A", "Blackrock", "MK3", "Alpha", "Azimut", "MPPV", "Belt")

        for(i in armors.indices){
            list.add(RecyclerMain(armors[i], names[i], context!!, RigActivity::class.java, "h_${names[i]}||"))
        }
        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_rig_chest_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}