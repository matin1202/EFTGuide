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

class HelmetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_helmet, container, false)

        val list = arrayListOf<RecyclerMain>()

        val helmets = mutableListOf(R.drawable.nvg_mask, R.drawable.slock, R.drawable.tk_fast_mt, R.drawable.tank_crew, R.drawable.kolpak_1s, R.drawable.firefighter, R.drawable.djeta, R.drawable.pumpkin, R.drawable.untar_helmet, R.raw.h_6b47, R.drawable.lzsh, R.drawable.ssh_68, R.drawable.kiver_m, R.drawable.ronin, R.drawable.sssh_95, R.drawable.tc_2001, R.drawable.tc_2002, R.drawable.tc_800, R.raw.achhc, R.raw.zsh_1_2m, R.raw.ulach, R.drawable.bastion, R.raw.fast_mt, R.drawable.airframe, R.raw.exfil, R.drawable.caiman, R.drawable.lshz_2dtm, R.raw.maska, R.drawable.altyn, R.drawable.rys_t, R.drawable.tagilla_mask, R.drawable.vulkan_5)
        val names = mutableListOf("NVG Mask", "Skull Lock head Mount", "Tac-Kek Fast MT", "Tank crew", "Kolpak-1s", "SHPM Firefighter", "PSH-97 \"Djeta\"", "Jack-o\'-lantern", "UNTAR", "6B47", "LZSh", "SSh-68", "Kiver-M", "DEVTAC Ronin", "SSSh-95", "TC-2001", "TC-2002", "TC-800", "ACHHC", "Zsh-1-2M", "ULACH", "Bastion", "Ops-core Fast MT", "Airframe Tan", "Team Wendy EXFIL", "Galvion Caiman", "LSHZ-2DTM", "Maska", "Altyn", "Rys-T", "Tagilla Mask", "Vulkan-5")

        for(i in helmets.indices){
            var extra = "h_${names[i]}||helmet"
            if(i == 9 || i == 18 || i == 19 || i == 20 || i == 22 || i == 24 || i == 27){
                extra += "||raw"
            }
            else{
                extra += "||"
            }
            list.add(RecyclerMain(helmets[i], names[i], context!!, ItemExplainActivity::class.java, extra))
        }

        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_helmet_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}