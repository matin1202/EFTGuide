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
import com.matin.eftguide.adapter.ammo.AmmoAdapter
import com.matin.eftguide.adapter.ammo.RecyclerAmmo

class ContainerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        val list = ArrayList<RecyclerAmmo>()

        list.add(
            RecyclerAmmo(
                R.drawable.container,
                getString(R.string.item_case),
                arrayListOf("JunkBox", "T H I C C Items case", "Item case", "T H I C C Weapon case", "Weapon case", "Meds Case", "Grenade case", "Mr. Holodilnick thermobag", "Magazine case", "Ammo case", "Money case", "Pistol case", "Document case", "Keytool", "Wallet", "Dogtag case"),
                arrayListOf(R.drawable.junkbox, R.drawable.thicc_item, R.drawable.item_case, R.drawable.thicc_weapon, R.drawable.weapon_case, R.drawable.meds_case, R.drawable.grenade_case, R.drawable.thermobag_case, R.drawable.magazine_case, R.drawable.ammo_case, R.drawable.money_case, R.drawable.pistol_case, R.drawable.document_case, R.drawable.keytool, R.drawable.wallet_case, R.drawable.dogtag_case)
                ,context!!, "container")
        )

        list.add(
            RecyclerAmmo(
                R.drawable.secure_container,
                getString(R.string.secure_container),
                arrayListOf("Alpha container", "Beta container", "Epsilon container", "Gamma container", "Kappa container"),
                arrayListOf(R.drawable.alpha_container, R.drawable.beta_container, R.drawable.epsilon_container, R.drawable.gamma_container, R.drawable.kappa_container)
                ,context!!, "secure")
        )

        val adapter = AmmoAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_item_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}