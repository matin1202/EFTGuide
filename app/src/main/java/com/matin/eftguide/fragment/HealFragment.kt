package com.matin.eftguide.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.R
import com.matin.eftguide.adapter.ammo.AmmoAdapter
import com.matin.eftguide.adapter.ammo.RecyclerAmmo

class HealFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        val list = ArrayList<RecyclerAmmo>()

        list.add(
            RecyclerAmmo(
                R.drawable.healkit,
                getString(R.string.healkit),
                arrayListOf("Analgin painkillers", "Augmentin", "Morphine", "Ibuprofen", "Aseptic bandage", "Army bandage", "AI-2", "Car kit", "Salewa", "IFAK", "Grizzly", "splint", "splint (alu)", "Vaseline", "Golden Star Balm", "CMS kit", "Surv12 kit", "Esmarch tourniquet", "CALOK-B Hemostatic"),
                arrayListOf(R.drawable.icon_painkillers, R.drawable.icon_augmentin, R.drawable.icon_morphine, R.drawable.icon_ibuprofen, R.drawable.icon_aseptic_bandage, R.drawable.icon_army_bandage, R.drawable.icon_ai2, R.drawable.icon_car_first_aid_kit, R.drawable.icon_salewa_first_aid_kit, R.drawable.icon_ifak, R.drawable.icon_grizzly, R.drawable.icon_splint, R.drawable.icon_alu_splint, R.drawable.icon_vaseline, R.drawable.icon_golden_star_balm, R.drawable.icon_cms, R.drawable.icon_surv12, R.drawable.icon_esmarch_tourniquet, R.drawable.icon_calok_b_hemostatic)

                ,context!!, "heal")
        )

        list.add(
            RecyclerAmmo(
                R.drawable.stimulator,
                getString(R.string.stimulator),
                arrayListOf("SJ1 TGLabs", "eTG-change", "SJ6 TGLabs", "Propital", "Zagustin", "Adrenaline", "Meldonin", "AHF1-M", "3-(b-TG)", "L1 (Norepinephrine)", "P22", "Cocktail \"Obdolbos\"", "M.U.L.E. stimulator", "Antidote xTG-12"),
                arrayListOf(R.drawable.icon_combat_stimulant_sj1, R.drawable.icon_etg_change, R.drawable.icon_combat_stimulant_sj6, R.drawable.icon_propital, R.drawable.icon_hemostatic_drug_zagustin, R.drawable.icon_adrenaline, R.drawable.icon_meldonin, R.drawable.icon_ahf1_m, R.drawable.icon_3_b_tg, R.drawable.icon_l1_norepinephrine, R.drawable.icon_p22, R.drawable.icon_cocktail_obdolbos, R.drawable.icon_mule_stimulator, R.drawable.icon_xtg_12),
                context!!, "stimulator")
        )

        val adapter = AmmoAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_item_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}