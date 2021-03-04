package com.matin.eftguide.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.*
import kotlinx.android.synthetic.main.ammo_list.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource

class WeaponFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weapon, container, false)

        val list = ArrayList<WFRecycler>()

        list.add(WFRecycler(context,
            R.drawable.assault,
            "Assault Rifles",
            arrayListOf("AK-101", "AK-102", "AK-103", "AK-104", "AK-105", "AK-74", "AK-74M", "AK-74N", "AKS-74", "AKS-74U", "AKS-74UN", "AKS-74UB", "AKS-74N", "AKM", "AKMN", "AKMS", "AKMSN", "HK416A5", "M4A1", "MCX", "RFB", "DT MDR 5.56x45", "DT MDR .308", "ADAR 2-15", "SA-58", "TX-15", "ASH-12", "Vepr AKM/VPO-209", "Vepr AKM/VPO-136"), arrayListOf(R.drawable.ak_101, R.drawable.ak_102, R.drawable.ak_103, R.drawable.ak_104, R.drawable.ak_105, R.drawable.ak_74, R.drawable.ak_74m, R.drawable.ak_74n, R.drawable.aks_74, R.drawable.aks_74u, R.drawable.aks_74un, R.drawable.aks_74ub, R.drawable.aks_74n, R.drawable.akm, R.drawable.akmn, R.drawable.akms, R.drawable.akmsn, R.drawable.hk416a1, R.drawable.m4a1, R.drawable.mcx, R.drawable.rfb, R.drawable.dt_mdr_5, R.drawable.dt_mdr_308, R.drawable.adar2_15, R.drawable.sa_58, R.drawable.tx_15, R.drawable.ash_12, R.drawable.vpo_209, R.drawable.vpo_136),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.assault,
            "Light Machine Gun",
            arrayListOf("RPK-16"),
            arrayListOf(R.drawable.rpk_16),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.assault,
            "Assault Carbines",
            arrayListOf("AS VAL", "OP-SKS", "SKS", "Vepr Hunter/VPO-101"),
            arrayListOf(R.drawable.asval, R.drawable.op_sks, R.drawable.sks, R.drawable.hunter),  "weapon"
        ))

        list.add(WFRecycler(context,
            R.drawable.weapon,
            "SubMachine Guns",
            arrayListOf("MP5", "MP5k", "MP7A1", "MP7A2", "MP9", "MP9-N", "MPX", "Vector 9x19", "Vector 45", "UMP 45", "P90", "PP-19-01 Vityaz-SN", "PP-9 \"Klin\"", "PP-91 \"Kedr\"", "PP-91-01 \"Kedr-B\"", "PPSH-41", "Saiga-9"),
            arrayListOf(R.drawable.mp5, R.drawable.mp5k_n, R.drawable.mp7a1, R.drawable.mp7a2, R.drawable.mp9, R.drawable.mp9_n, R.drawable.mpx, R.drawable.vector_9, R.drawable.vector_45, R.drawable.ump_45, R.drawable.p90, R.drawable.vityza_sn, R.drawable.klin, R.drawable.kedr, R.drawable.kedr_b, R.drawable.ppsh_41, R.drawable.saiga9),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.shotgun,
            "Shotguns",
            arrayListOf("M870", "MP-133", "MP-153", "KS-23M", "Saiga-12", "Toz-106"),
            arrayListOf(R.drawable.m870, R.drawable.mp_133, R.drawable.mp153, R.drawable.ks_23m, R.drawable.saiga_12, R.drawable.toz_106),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.sniper,
            "Marksman Rifles",
            arrayListOf("M1A", "RSASS", "SR-25", "SVDs", "VSS", "Mk-18"),
            arrayListOf(R.drawable.m1a, R.drawable.raass, R.drawable.sr_25, R.drawable.svds, R.drawable.vss, R.drawable.mk18),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.sniper,
            "Sniper Rifles",
            arrayListOf("DVL-10", "M700", "Mosin", "Mosin Inf.", "SV-98", "T-5000", "VPO-215"),
            arrayListOf(R.drawable.dvl_10, R.drawable.m700, R.drawable.mosin, R.drawable.mosin_inf, R.drawable.sv_98, R.drawable.t_5000, R.drawable.vpo_215),  "weapon"))

        list.add(
            WFRecycler(context,
            R.drawable.grenade_launcher,
                "Grenade Launchers",
                arrayListOf("FN GL40"),
                arrayListOf(R.drawable.fn_gl40),
                 "weapon"))

        list.add(WFRecycler(context,
            R.drawable.pistol,
            "Pistols",
            arrayListOf("APB", "APS", "FN 5-7", "Glock 17", "Glock 18c", "M1911A1", "M45A1", "M9A3", "MP-443 \"Grach\"", "P226R", "PB pistol", "PM (t) pistol", "PM pistol", "SR-1MP Gyurza", "TT pistol", "TT pistol (gold)"),
            arrayListOf(R.drawable.apb, R.drawable.aps, R.drawable.fn5_7, R.drawable.glock17, R.drawable.glock_18c, R.drawable.m1911a1, R.drawable.m45a1, R.drawable.m9a3, R.drawable.mp_443, R.drawable.p226r, R.drawable.pb, R.drawable.pm_t, R.drawable.pm, R.drawable.sr_1mp, R.drawable.tt, R.drawable.tt_gold),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.machine_gun,
            "Stationary Weapon",
            arrayListOf("NSV \"Utes\"", "AGS-30"),
            arrayListOf(R.drawable.nsv, R.drawable.ags_30),  "weapon"))

        list.add(WFRecycler(context,
            R.drawable.melee,
            "Melee Weapon",
            arrayListOf("6h5 Bayonet", "Antique axe", "Bars A-2607-95x18", "Bars A-2607-Damascus", "Camper Axe", "Crash Axe", "ER Fulcrum Bayonet", "CrowBar", "Tactical Tomahawk", "M-2 Tactical Sword", "Cultists knife", "MPL-50", "Red Rebel Ice Pick", "SP-8 Machete"),
            arrayListOf(R.drawable.bayonet_6h5, R.drawable.antique_axe, R.drawable.a_2607_9, R.drawable.a_2607_d, R.drawable.camper_axe, R.drawable.crash_axe, R.drawable.er_bayonet, R.drawable.crowbar, R.drawable.tomahawk, R.drawable.m_2, R.drawable.cultist_knife, R.drawable.mpl_50, R.drawable.rr, R.drawable.sp_8),  "meleeweapon"))

        list.add(WFRecycler(context,
            R.drawable.grenade,
            "Throwable Weapon",
            arrayListOf("F-1", "M67", "RGD-5", "VOG-17", "VOG-25", "RDG-2B", "Zarya"),
            arrayListOf(R.drawable.f_1, R.drawable.m67, R.drawable.rgd_5, R.drawable.vog_17, R.drawable.vog_25, R.drawable.rdg_2b, R.drawable.zarya),  "weapon"))


        val adapter = WFAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_weapon_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        return view
    }
}


class WFRecycler(val context: Context?, val weapon: Int, val title: String, val text: ArrayList<String>, val image: ArrayList<Int>, val type: String)

class WF2Recycler(val context: Context?, val image: Int, val title: String, val type: String)

class WFAdapter(private val items: ArrayList<WFRecycler>):
    RecyclerView.Adapter<WFAdapter.ViewHolder>(){
    private var previousPosition = -1

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: WFRecycler){

            view.ammo_textView.text = item.title
            view.ammo_expansion.imageResource = R.drawable.ic_baseline_expand_more_24

            view.ammo_imageView.imageResource = item.weapon

            view.setOnClickListener { v->
                if(v.ammo_menu.visibility == View.GONE){
                    v.ammo_menu.visibility = View.VISIBLE
                    v.ammo_expansion.imageResource = R.drawable.ic_baseline_expand_less_24
                }
                else{
                    v.ammo_menu.visibility = View.GONE
                    v.ammo_expansion.imageResource = R.drawable.ic_baseline_expand_more_24
                }
            }

            val list = ArrayList<WF2Recycler>()
            for(i in item.image.indices){
                list.add(WF2Recycler(item.context, item.image[i], item.text[i], item.type))
            }

            view.ammo_menu.adapter = WF2Adapter(list)
            view.ammo_menu.layoutManager = LinearLayoutManager(item.context)
            view.ammo_menu.addItemDecoration(DividerItemDecoration(item.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.ammo_list, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position > previousPosition ){
            val ani = AlphaAnimation(0.0f, 2.0f)
            ani.fillAfter = true
            ani.duration = 1000
            holder.itemView.animation = ani
        }

        previousPosition = position
        val item = items[position]
        holder.apply{
            bind(item)
            itemView.tag = item
        }
    }

    class WF2Adapter(private val items: ArrayList<WF2Recycler>):
        RecyclerView.Adapter<WF2Adapter.ViewHolder>(){
        private var previousPosition = -1

        class ViewHolder(v: View): RecyclerView.ViewHolder(v){
            val view = v
            fun bind(item: WF2Recycler){
                view.main_textView.text = item.title
                view.main_imageView.imageResource = item.image
                view.main_textView.textSize = 18.0f

                view.setOnClickListener{
                    item.context?.startActivity(
                        Intent(item.context, ItemExplainActivity::class.java)
                            .putExtra(
                            "type",
                            "w_${item.title}||${item.type}")
                            .putExtra("image", item.image)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                    return@setOnClickListener
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.menu_list, parent, false)
            return ViewHolder(inflatedView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(position > previousPosition ){
                val ani = AlphaAnimation(0.0f, 2.0f)
                ani.fillAfter = true
                ani.duration = 1000
                holder.itemView.animation = ani
            }

            previousPosition = position
            val item = items[position]
            holder.apply{
                bind(item)
                itemView.tag = item
            }
        }


    }
}