package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.R
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain

class ArmorVestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armor_vest, container, false)

        val list = arrayListOf<RecyclerMain>()

        val armors = mutableListOf(R.drawable.a_3m, R.drawable.paca, R.drawable.paca_rivals, R.drawable.a_6b2, R.drawable.untar, R.drawable.zhuk_3, R.drawable.a_6b23_1icon, R.drawable.kirasa, R.drawable.thor_concealable, R.drawable.trooper, R.raw.a_6b13, R.drawable.a_6b23_2, R.drawable.korund, R.drawable.redut_m, R.drawable.a_6b13m, R.drawable.gen4_hmk, R.drawable.gzhel_k, R.drawable.defender_2, R.drawable.gen4_assault, R.drawable.gen4_full, R.drawable.redut_t5, R.drawable.hexgrid, R.drawable.thor_integrated, R.raw.a_slick, R.drawable.zhuk_6a, R.drawable.zabralo)
        val names = mutableListOf("Module-3M", "PACA", "PACA (Rivals)", "6B2", "MF-UNTAR", "Zhuk-3", "6B23-1", "Kirasa-N", "THOR Concealable", "Trooper TFO", "6B13", "6B23-2", "Korund-VM", "FORT Redut-M", "6B13 M", "Gen4(High Mobility kit)", "Gzhel-K", "FORT Defender-2", "Gen4(Assault kit)", "Gen4(Full protection)", "FORT Redut-T5", "Hexgrid", "THOR Integrated", "Slick", "Zhuk-6a", "6B43")
        for(i in armors.indices){
            var extra = "h_${names[i]}||armor_vest"
            extra += if(i == 8 || i == 20){
                "||raw"
            } else{
                "||"
            }
            list.add(RecyclerMain(armors[i], names[i], context!!, ItemExplainActivity::class.java, extra))
        }

        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_armor_vest_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}