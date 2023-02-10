package com.matin.eftguide.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
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
import com.matin.eftguide.data.Bullet
import com.matin.eftguide.data.Datas
import com.matin.eftguide.databinding.FragmentAmmoBinding
import kotlinx.android.synthetic.main.ammo_list.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource


class AmmoFragment : Fragment() {
    private var _binding: FragmentAmmoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAmmoBinding.inflate(inflater, container, false)
        val view = binding.root

        val list = ArrayList<AFRecycler>()

        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_12,
                "12/70",
                Datas.findBulletByCaliber("12/70"),
                arrayListOf(R.drawable.buck_525_12, R.drawable.buck_85_12, R.drawable.buck_65_12, R.drawable.buck_7_12, R.drawable.flechette_12, R.drawable.rip_12, R.drawable.sf_12, R.drawable.grizzly_12, R.drawable.csp_12, R.drawable.led_12, R.drawable.poleva3_12, R.drawable.dual_sabot_12, R.drawable.ftx_12, R.drawable.poleva6_12, R.drawable.bmg_12, R.drawable.ap_20_12)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_20,
                "20/70",
                Datas.findBulletByCaliber("20/70"),
                arrayListOf(R.drawable.buck_56_20, R.drawable.explosive_20, R.drawable.buck_62_20, R.drawable.buck_75_20, R.drawable.buck_73_20, R.drawable.devastator_20, R.drawable.poleva3_20, R.drawable.star_20, R.drawable.poleva6u_20, R.drawable.flechetta_20, R.drawable.elephant_20)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_23,
                "23x75mm",
                Datas.findBulletByCaliber("23x75mm"),
                arrayListOf(R.drawable.star_2375, R.drawable.shrapnel_25_2375, R.drawable.shrapnel_10_2375, R.drawable.barricade_23)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_918,
                "9x18mm",
                Datas.findBulletByCaliber("9x18mm"),
                arrayListOf(R.drawable.pmb_18, R.drawable.pmm_18, R.drawable.bzt_gzh_18, R.drawable.rg028_gzh_18, R.drawable.pst_gzh_18, R.drawable.ppt_gzh_18, R.drawable.ppe_gzh_18, R.drawable.prs_gs_18, R.drawable.ps_gs_ppo_18, R.drawable.pso_gzh_18, R.drawable.p_gzh_18, R.drawable.psv_18, R.drawable.sp7_gzh_18, R.drawable.sp8_gzh_18).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_919,
                "9x19mm",
                Datas.findBulletByCaliber("9x19mm"),
                arrayListOf(R.drawable.n7n31_19, R.drawable.ap_19, R.drawable.pst_19, R.drawable.gt_19, R.drawable.luger_19, R.drawable.pso_19, R.drawable.quakemaker_19, R.drawable.rip_19).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_357,
                ".357 Magnum",
                Datas.findBulletByCaliber(".357 Magnum"),
                arrayListOf(R.drawable.sp_357, R.drawable.hp_357, R.drawable.jhp_357, R.drawable.fmj_357)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_725,
                "7.62x25mm",
                Datas.findBulletByCaliber("7.62x25mm"),
                arrayListOf(R.drawable.tt_lrnpc, R.drawable.tt_lrn, R.drawable.tt_fmj43, R.drawable.tt_akbs, R.drawable.tt_pgl, R.drawable.tt_pt_gzh, R.drawable.tt_pst_gzh)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_45,
                ".45 ACP",
                Datas.findBulletByCaliber(".45 ACP"),
                arrayListOf(R.drawable.ap_45 ,R.drawable.fmj_45, R.drawable.lasermatch_45, R.drawable.hydrashok_45, R.drawable.rip_45).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_921,
                "9x21mm",
                Datas.findBulletByCaliber("9x21mm"),
                arrayListOf(R.drawable.sp13_21, R.drawable.sp10_21, R.drawable.sp11_21, R.drawable.sp12_21).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_57,
                "5.7x28mm",
                Datas.findBulletByCaliber("5.7x28mm"),
                arrayListOf(R.drawable.r37f_57, R.drawable.ss198lf_57, R.drawable.r37x_57, R.drawable.ss197sr_57, R.drawable.l191_57, R.drawable.sb193_57, R.drawable.ss190_57)
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_46,
                "4.6x30mm",
                Datas.findBulletByCaliber("4.6x30mm"),
                arrayListOf(R.drawable.ap_46, R.drawable.fmj_46, R.drawable.subsonic_46, R.drawable.action_46).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_939,
                "9x39mm",
                Datas.findBulletByCaliber("9x39mm"),
                arrayListOf(R.drawable.bp_939, R.drawable.spp_939, R.drawable.pab9_939, R.drawable.sp6_939, R.drawable.sp5_939).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_366,
                ".366 TKM",
                Datas.findBulletByCaliber(".366 TKM"),
                arrayListOf(R.drawable.ap_366, R.drawable.eko_366, R.drawable.fmj_366, R.drawable.geksa_366).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_545,
                "5.45x39mm",
                Datas.findBulletByCaliber("5.45x39mm"),
                arrayListOf(R.drawable.igolnik_545, R.drawable.bs_545, R.drawable.bt_545, R.drawable.bp_545, R.drawable.pp_545, R.drawable.ps_545, R.drawable.t_545, R.drawable.fmj_545, R.drawable.us_545, R.drawable.prs_545, R.drawable.hp_545, R.drawable.sp_545).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_556,
                "5.56x45mm",
                Datas.findBulletByCaliber("5.56x45mm"),
                arrayListOf(R.drawable.ssa_ap_556, R.drawable.m995_556, R.drawable.m855a1_556, R.drawable.m856a1_556, R.drawable.m855_556, R.drawable.fmj_556, R.drawable.m856_556, R.drawable.mk_318_556, R.drawable.mk255_556, R.drawable.hp_556, R.drawable.warmage_556).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_739,
                "7.62x39mm",
                Datas.findBulletByCaliber("7.62x39mm"),
                arrayListOf(R.drawable.mai_ap_739, R.drawable.bp_739, R.drawable.ps_739, R.drawable.t45m_739, R.drawable.us_739, R.drawable.hp_739).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_300,
                ".300 Blackout",
                Datas.findBulletByCaliber(".300 Blackout"),
                arrayListOf(R.drawable.whisper_300, R.drawable.vmax_300 ,R.drawable.bpz_300, R.drawable.m62_300, R.drawable.ap_300).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_751,
                "7.62x51mm",
                Datas.findBulletByCaliber("7.62x51mm"),
                arrayListOf(R.drawable.m933_751 ,R.drawable.m61_751, R.drawable.m62_751, R.drawable.m80_751, R.drawable.bpz_fmj_751, R.drawable.tpz_sp_751, R.drawable.ultra_nosier_751).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_754,
                "7.62x54mm R",
                Datas.findBulletByCaliber("7.62x54mm R"),
                arrayListOf(R.drawable.r54_bs, R.drawable.r54_snb, R.drawable.r54_7bt1, R.drawable.r54_7n1, R.drawable.r54_lps_gzh, R.drawable.r54_t46m).reversed()
            )
        )
       /* list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_40,
                "40x46",
                arrayListOf("M381", "M386", "M406", "M433", "M441", "M576"),
                arrayListOf(R.drawable.m381_40, R.drawable.m386_40, R.drawable.m406_40, R.drawable.m433_40, R.drawable.m441_40, R.drawable.m576_40)

            )
        )*/
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_127,
                "12.7x55mm",
                Datas.findBulletByCaliber("12.7x55mm"),
                arrayListOf(R.drawable.ps12b_127, R.drawable.ps12_127, R.drawable.ps12a_127).reversed()
            )
        )
        list.add(
            AFRecycler(
                requireContext(), R.raw.ammo_338,
                ".338 Lapua Magnum",
                Datas.findBulletByCaliber(".338 Lapua Magnum"),
                arrayListOf(R.drawable.ap_338, R.drawable.fmj_338, R.drawable.upz_338, R.drawable.tac_x_338).reversed()
            )
        )
        /*list.add(
            AFRecycler(
                requireContext(),
                R.raw.ammo_108,
                "12.7x108",
                arrayListOf("B-32", "BZT-44M"),
                arrayListOf(R.drawable.b_32_108, R.drawable.bzt_44m_108))
        )*/

        val adapter = AFAdapter(list)

        val recyclerView: RecyclerView = view.findViewById(R.id.rl_ammo_menu)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        return view
    }
}

class AFRecycler(val context: Context, val ammo: Int, val title: String, val bullets: List<Bullet>, val image: List<Int>)

class AF2Recycler(val context: Context, val image: Int, val bullet: Bullet)

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
                list.add(AF2Recycler(item.context, item.bullets[i].image, item.bullets[i]))
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

    override fun onBindViewHolder(holder: AFAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val _position = holder.adapterPosition
        if(_position > previousPosition ){
            val ani = AlphaAnimation(0.0f, 2.0f)
            ani.fillAfter = true
            ani.duration = 1000
            holder.itemView.animation = ani
        }

        previousPosition = _position
        val item = items[_position]
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
                view.main_textView.text = item.bullet.name
                view.main_imageView.imageResource = item.image
                view.main_textView.textSize = 18.0f

                view.setOnClickListener{ _ ->
                    item.context.startActivity(Intent(item.context, ExplainActivity::class.java).putExtra("image", item.image).putExtra("bullet",
                        item.bullet.name).addFlags(FLAG_ACTIVITY_NEW_TASK))
                    return@setOnClickListener
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
            return ViewHolder(inflatedView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
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