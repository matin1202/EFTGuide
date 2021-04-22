package com.matin.eftguide.fragment

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ExplainActivity
import com.matin.eftguide.R
import com.matin.eftguide.base.loadWithWebp
import kotlinx.android.synthetic.main.ammo_list.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource


class AmmoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ammo, container, false)

        val list = ArrayList<AFRecycler>()

        list.add(
            AFRecycler(
                context!!, R.raw.ammo_12,
                "12/70",
                arrayListOf("Flechette", "AP-20 Slug", "shell with .50 BMG bullet", "\"Poleva-6a\" Slug", "FTX Custom Llte Slug", "\"Poleva-3\" Slug", "Dual Sabot Slug", "Led Slug", "HP Slug Copper Sabot Premier", "Grizzly 40 Slug", "7mm Buckshot","6.5mm \"Express\" Buckshot", "8.5mm \"Magnum\" Buckshot", "5.25mm Buckshot", "HP Slug \"SuperFormance\"", "RIP"),
                arrayListOf(R.drawable.flechette_12, R.drawable.ap_20_12, R.drawable.bmg_12, R.drawable.poleva6_12, R.drawable.ftx_12, R.drawable.poleva3_12, R.drawable.dual_sabot_12, R.drawable.led_12, R.drawable.csp_12, R.drawable.grizzly_12, R.drawable.buck_7_12, R.drawable.buck_65_12, R.drawable.buck_85_12, R.drawable.buck_525_12, R.drawable.sf_12, R.drawable.rip_12)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_20,
                "20/70",
                arrayListOf("Slug \"Poleva-6u\"", "Star Slug", "Slug \"Poleva-3\"", "7.3mm Buckshot", "7.5mm Buckshot", "6.2mm Buckshot", "5.6mm Buckshot", "Devastator Slug"),
                arrayListOf(R.drawable.poleva6u_20, R.drawable.star_20, R.drawable.poleva3_20, R.drawable.buck_73_20, R.drawable.buck_75_20, R.drawable.buck_62_20, R.drawable.buck_56_20, R.drawable.devastator_20)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_23,
                "23/75",
                arrayListOf("Shrapnel-25", "Shrapnel-10", "\"Barricade\"", "\"Star\""),
                arrayListOf(R.drawable.shrapnel_25_2375, R.drawable.shrapnel_25_2375, R.drawable.barricade_23, R.drawable.star_2375)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_918,
                "9x18",
                arrayListOf("PM PBM", "PM PMM", "PM 9 BZT gzh", "PM RG028 gzh", "PM Pst gzh", "PM PPT gzh", "PM PPe gzh", "PM PRS gs", "PM PS gs PPO", "PM PSO gzh", "PM 9 P gzh", "PM PSV", "PM SP7 gzh", "PM SP8 gzh"),
                arrayListOf(R.drawable.pmb_18, R.drawable.pmm_18, R.drawable.bzt_gzh_18, R.drawable.rg028_gzh_18, R.drawable.pst_gzh_18, R.drawable.ppt_gzh_18, R.drawable.ppe_gzh_18, R.drawable.prs_gs_18, R.drawable.ps_gs_ppo_18, R.drawable.pso_gzh_18, R.drawable.p_gzh_18, R.drawable.psv_18, R.drawable.sp7_gzh_18, R.drawable.sp8_gzh_18)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_919,
                "9x19",
                arrayListOf("7N31","AP 6.3", "Pst gzh", "Green Tracer", "Luger CCI", "PSO gzh", "QuakeMaker", "RIP"),
                arrayListOf(R.drawable.n7n31_19, R.drawable.ap_19, R.drawable.pst_19, R.drawable.gt_19, R.drawable.luger_19, R.drawable.pso_19, R.drawable.quakemaker_19, R.drawable.rip_19)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_725,
                "7.62x25",
                arrayListOf("TT LRNPC", "TT LRN", "TT FMJ43", "TT AKBS", "TT P gl", "TT PT gzh", "TT Pst gzh").reversed() as ArrayList<String>,
                arrayListOf(R.drawable.tt_lrnpc, R.drawable.tt_lrn, R.drawable.tt_fmj43, R.drawable.tt_akbs, R.drawable.tt_pgl, R.drawable.tt_pt_gzh, R.drawable.tt_pst_gzh).reversed() as ArrayList<Int>
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_45,
                ".45 ACP",
                arrayListOf("AP","FMJ", "Lasermatch FMJ", "HydraShock", "RIP"),
                arrayListOf(R.drawable.ap_45 ,R.drawable.fmj_45, R.drawable.lasermatch_45, R.drawable.hydrashok_45, R.drawable.rip_45)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_921,
                "9x21",
                arrayListOf("SP13", "SP10", "SP11", "SP12"),
                arrayListOf(R.drawable.sp13_21, R.drawable.sp10_21, R.drawable.sp11_21, R.drawable.sp12_21)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_57,
                "5.7x28",
                arrayListOf("R37.F", "SS198LF", "R37.X", "SS197SR", "L191", "SB193", "SS190").reversed() as ArrayList<String>,
                arrayListOf(R.drawable.r37f_57, R.drawable.ss198lf_57, R.drawable.r37x_57, R.drawable.ss197sr_57, R.drawable.l191_57, R.drawable.sb193_57, R.drawable.ss190_57).reversed() as ArrayList<Int>
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_46,
                "4.6x30",
                arrayListOf("AP SX", "FMJ SX", "Subsonic SX", "Action SX"),
                arrayListOf(R.drawable.ap_46, R.drawable.fmj_46, R.drawable.subsonic_46, R.drawable.action_46)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_939,
                "9x39",
                arrayListOf("7N12 BP", "7N9 SPP", "SP-6", "SP-5"),
                arrayListOf(R.drawable.bp_939, R.drawable.spp_939, R.drawable.sp6_939, R.drawable.sp5_939)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_366,
                ".366",
                arrayListOf("AP", "EKO", "FMJ", "Geksa"),
                arrayListOf(R.drawable.ap_366, R.drawable.eko_366, R.drawable.fmj_366, R.drawable.geksa_366)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_545,
                "5.45x39",
                arrayListOf("7N39 \"Igolnik\"", "BS", "BT", "BP", "PP", "PS", "T", "FMJ", "US", "PRS", "HP", "SP" ),
                arrayListOf(R.drawable.igolnik_545, R.drawable.bs_545, R.drawable.bt_545, R.drawable.bp_545, R.drawable.pp_545, R.drawable.ps_545, R.drawable.t_545, R.drawable.fmj_545, R.drawable.us_545, R.drawable.prs_545, R.drawable.hp_545, R.drawable.sp_545)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_556,
                "5.56x45",
                arrayListOf("SSA AP", "M995", "M855A1", "M856A1", "M855", "55 FMJ", "M856", "Mk 318 Mod 0", "Mk 255 Mod 0", "55 HP", "Warmage"),
                arrayListOf(R.drawable.ssa_ap_556, R.drawable.m995_556, R.drawable.m855a1_556, R.drawable.m856a1_556, R.drawable.m855_556, R.drawable.fmj_556, R.drawable.m856_556, R.drawable.mk_318_556, R.drawable.mk255_556, R.drawable.hp_556, R.drawable.warmage_556)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_739,
                "7.62x39",
                arrayListOf("MAI AP", "BP", "PS", "T-45M", "US", "HP"),
                arrayListOf(R.drawable.mai_ap_739, R.drawable.bp_739, R.drawable.ps_739, R.drawable.t45m_739, R.drawable.us_739, R.drawable.hp_739)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_300,
                ".300",
                arrayListOf("BPZ", "AP"),
                arrayListOf(R.drawable.bpz_300, R.drawable.ap_300)
            )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_751,
                "7.62x51",
                arrayListOf("M993", "M61", "M62", "M80", "TPZ SP", "BPZ FMJ", "Ultra Nosler"),
                arrayListOf(R.drawable.m933_751 ,R.drawable.m61_751, R.drawable.m62_751, R.drawable.m80_751, R.drawable.bpz_fmj_751, R.drawable.tpz_sp_751, R.drawable.ultra_nosier_751)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_754,
                "7.62x54",
                arrayListOf("7N37", "SNB", "7BT1", "7N1", "LPS Gzh", "T-46M"),
                arrayListOf(R.drawable.r54_7n37, R.drawable.r54_snb, R.drawable.r54_7bt1, R.drawable.r54_7n1, R.drawable.r54_lps_gzh, R.drawable.r54_t46m)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_40,
                "40x46",
                arrayListOf("M381", "M386", "M406", "M433", "M441", "M576"),
                arrayListOf(R.drawable.m381_40, R.drawable.m386_40, R.drawable.m406_40, R.drawable.m433_40, R.drawable.m441_40, R.drawable.m576_40)
                
            )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_127,
                "12.7x55",
                arrayListOf("PS12B", "PS12", "PS12A"),
                arrayListOf(R.drawable.ps12b_127, R.drawable.ps12_127, R.drawable.ps12a_127)
                )
        )
        list.add(
            AFRecycler(
                context!!, R.raw.ammo_338,
                ".338",
                arrayListOf("AP", "FMJ", "UPZ", "Tac-X"),
                arrayListOf(R.drawable.ap_338, R.drawable.fmj_338, R.drawable.upz_338, R.drawable.tac_x_338)
            )
        )
        list.add(
            AFRecycler(
                context!!,
                R.raw.ammo_108,
                "12.7x108",
                arrayListOf("B-32", "BZT-44M"),
                arrayListOf(R.drawable.b_32_108, R.drawable.bzt_44m_108))
        )

        val adapter = AFAdapter(list)

        val recyclerView: RecyclerView = view.findViewById(R.id.rl_ammo_menu)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        return view
    }
}

class AFRecycler(val context: Context, val ammo: Int, val title: String, val text: ArrayList<String>, val image: ArrayList<Int>)

class AF2Recycler(val context: Context, val image: Int, val title: String, val type: String)

class AFAdapter(private val items: ArrayList<AFRecycler>):
    RecyclerView.Adapter<AFAdapter.ViewHolder>(){
    private var previousPosition = -1

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: AFRecycler){

            view.ammo_textView.text = item.title
            view.ammo_expansion.imageResource = R.drawable.ic_baseline_expand_more_24

            loadWithWebp(item.context, view.ammo_imageView, item.ammo)

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

            val list = ArrayList<AF2Recycler>()
            for(i in item.image.indices){
                list.add(AF2Recycler(item.context, item.image[i], item.text[i], item.title))
            }

            view.ammo_menu.adapter = AF2Adapter(list)
            view.ammo_menu.layoutManager = LinearLayoutManager(item.context)
            view.ammo_menu.addItemDecoration(DividerItemDecoration(item.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.ammo_list, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AFAdapter.ViewHolder, position: Int) {
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

    class AF2Adapter(private val items: ArrayList<AF2Recycler>):
            RecyclerView.Adapter<AF2Adapter.ViewHolder>(){
        private var previousPosition = -1

        class ViewHolder(v: View): RecyclerView.ViewHolder(v){
            val view = v
            fun bind(item: AF2Recycler){
                view.main_textView.text = item.title
                view.main_imageView.imageResource = item.image
                view.main_textView.textSize = 18.0f

                view.setOnClickListener{ _ ->
                    item.context.startActivity(Intent(item.context, ExplainActivity::class.java).putExtra("type", "a${item.type}_${item.title}").addFlags(FLAG_ACTIVITY_NEW_TASK))
                    return@setOnClickListener
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
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