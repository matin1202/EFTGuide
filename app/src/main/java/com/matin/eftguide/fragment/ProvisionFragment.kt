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


class ProvisionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        val list = ArrayList<RecyclerAmmo>()

        list.add(
            RecyclerAmmo(
                R.drawable.energy,
                getString(R.string.provision),
                arrayListOf("Army Crackers", "Condensed Milk", "Slickers Bar", "Pack of Sugar", "Can of pacific Saury", "Rye Croutons", "Emelya Rye Croutons", "Humpback Salmon", "Can of Green Peas", "Can of Delicious Beef Stew", "Can of Beef stew", "Pack of Oat Flakes", "Can of Herring", "Squash Spread", "Alyonka Chocolate Bar", "Iskra Lunch Box", "Water Bottle", "Aquamari", "Pineapple Juice", "Green Ice", "Apple Juice", "Grand Juice", "Vita Juice", "Max Energy", "TarCola", "Milk", "Hot Rod", "Mayo", "Can of Sprats", "Purified Water", "Moonshine", "Vodka", "Whiskey"),
                arrayListOf(R.drawable.p_crackers, R.drawable.p_condmilk, R.drawable.p_slickers, R.drawable.p_sugar, R.drawable.p_saury, R.drawable.p_rye, R.drawable.p_emeyla, R.drawable.p_humpback, R.drawable.p_peas, R.drawable.p_delicous_beef_stew, R.drawable.p_beef_stew, R.drawable.p_oatflakes, R.drawable.p_herring, R.drawable.p_squash_spread, R.drawable.p_alyonka, R.drawable.p_lunchbox, R.drawable.p_water, R.drawable.p_aquamari, R.drawable.p_pineapple_juice, R.drawable.p_green_tea, R.drawable.p_apple_juice, R.drawable.p_grand_juice, R.drawable.p_vita_juice, R.drawable.p_max_energy, R.drawable.p_tar_cola, R.drawable.p_milk, R.drawable.p_hot_rod, R.drawable.p_mayo, R.drawable.p_sprats, R.drawable.p_purified_water, R.drawable.p_moonshine, R.drawable.p_vodka, R.drawable.p_whiskey)
                ,context!!, "provision")
        )

        val adapter = AmmoAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_item_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}