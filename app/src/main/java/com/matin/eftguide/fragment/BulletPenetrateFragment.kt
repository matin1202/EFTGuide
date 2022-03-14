package com.matin.eftguide.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.matin.eftguide.R
import com.matin.eftguide.base.loadWithWebp
import kotlinx.android.synthetic.main.fragment_bullet_penetrate.view.*
import kotlinx.android.synthetic.main.menu_armor_selection.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.textColor
import java.util.*
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.round

@Suppress("NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
@SuppressLint("SetTextI18n", "InflateParams")
class BulletPenetrateFragment : Fragment() {

    lateinit var currentArmor: String
    lateinit var currentAmmo: String
    private var thread: Thread? = null
    private lateinit var curAmmo: Array<String>
    private lateinit var curArmor: Array<String>
    var maxDur: Int = 0
    var curDur: Int = 0
    private var ammoList = arrayListOf<PenetrateAmmoList>()
    private var armorList = arrayListOf<PenetrateArmorList>()
    private lateinit var ammoAdapter: PenetrateAmmoAdapter
    private lateinit var armorAdapter: PenetrateArmorAdapter
    val namesAmmo = mutableListOf(
        "12/70 Flechette",
        "12/70 AP-20 Slug",
        "12/70 shell with .50 BMG bullet",
        "12/70 \"Poleva-6a\" Slug",
        "12/70 FTX Custom Llte Slug",
        "12/70 \"Poleva-3\" Slug",
        "12/70 Dual Sabot Slug",
        "12/70 Led Slug",
        "12/70 HP Slug Copper Sabot Premier",
        "12/70 Grizzly 40 Slug",
        "12/70 7`mm Buckshot",
        "12/70 6.5`mm \"Express\" Buckshot",
        "12/70 8.5`mm \"Magnum\" Buckshot",
        "12/70 5.25`mm Buckshot",
        "12/70 HP Slug \"SuperFormance\"",
        "12/70 RIP",
        "20/70 Slug \"Poleva-6u\"",
        "20/70 Star Slug",
        "20/70 Slug \"Poleva-3\"",
        "20/70 7.3`mm Buckshot",
        "20/70 7.5`mm Buckshot",
        "20/70 6.2`mm Buckshot",
        "20/70 5.6`mm Buckshot",
        "20/70 Devastator Slug",
        "23/75 Shrapnel-25",
        "23/75 Shrapnel-10",
        "23/75 \"Barrikada\"",
        "9x18mm PM PBM",
        "9x18mm PM PMM",
        "9x18mm PM 9 BZT gzh",
        "9x18mm PM RG028 gzh",
        "9x18mm PM Pst gzh",
        "9x18mm PM PPT gzh",
        "9x18mm PM PPe gzh",
        "9x18mm PM PRS gs",
        "9x18mm PM PS gs PPO",
        "9x18mm PM PSO gzh",
        "9x18mm PM 9 P gzh",
        "9x18mm PM PSV",
        "9x18mm PM SP7 gzh",
        "9x18mm PM SP8 gzh",
        "9x19mm PBP",
        "9x19mm AP 6.3",
        "9x19mm Pst gzh",
        "9x19mm Green Tracer",
        "9x19mm Luger CCI",
        "9x19mm PSO gzh",
        "9x19mm QuakeMaker",
        "9x19mm RIP",
        "7.62x25mm TT LRNPC",
        "7.62x25mm TT LRN",
        "7.62x25mm TT FMJ43",
        "7.62x25mm TT AKBS",
        "7.62x25mm TT P gl",
        "7.62x25mm TT PT gzh",
        "7.62x25mm TT Pst gzh",
        ".45 ACP AP",
        ".45 ACP FMJ",
        ".45 ACP Lasermatch FMJ",
        ".45 ACP HydraShock",
        ".45 ACP RIP",
        "9x21mm BT",
        "9x21mm PS",
        "9x21mm P",
        "9x21mm PE",
        "5.7x28mm R37.F",
        "5.7x28mm SS198LF",
        "5.7x28mm R37.X",
        "5.7x28mm SS197SR",
        "5.7x28mm L191",
        "5.7x28mm SB193",
        "5.7x28mm SS190",
        "4.6x30mm AP SX",
        "4.6x30mm FMJ SX",
        "4.6x30mm Subsonic SX",
        "4.6x30mm Action SX",
        "9x39mm 7N12 BP",
        "9x39mm 7N9 SPP",
        "9x39mm PAB-9",
        "9x39mm SP-6",
        "9x39mm SP-5",
        ".366 AP-M",
        ".366 EKO",
        ".366 FMJ",
        ".366 Geksa",
        "5.45x39mm PPBS \"Igolnik\"",
        "5.45x39mm BS",
        "5.45x39mm BT",
        "5.45x39mm BP",
        "5.45x39mm PP",
        "5.45x39mm PS",
        "5.45x39mm T",
        "5.45x39mm FMJ",
        "5.45x39mm US",
        "5.45x39mm PRS",
        "5.45x39mm HP",
        "5.45x39mm SP",
        "5.56x45mm SSA AP",
        "5.56x45mm M995",
        "5.56x45mm M855A1",
        "5.56x45mm M856A1",
        "5.56x45mm M855",
        "5.56x45mm 55 FMJ",
        "5.56x45mm M856",
        "5.56x45mm Mk 318 Mod 0",
        "5.56x45mm Mk 255 Mod 0",
        "5.56x45mm 55 HP",
        "5.56x45mm Warmageddon",
        "7.62x39mm MAI AP",
        "7.62x39mm BP",
        "7.62x39mm PS",
        "7.62x39mm T-45M",
        "7.62x39mm US",
        "7.62x39mm HP",
        ".300 Whisper",
        ".300 V-Max",
        ".300 BCP",
        ".300 M62",
        ".300 AP",
        "7.62x51mm M993",
        "7.62x51mm M61",
        "7.62x51mm M62",
        "7.62x51mm M80",
        "7.62x51mm SP",
        "7.62x51mm FMJ",
        "7.62x51mm Ultra Nosler",
        "7.62x54mm BS",
        "7.62x54mm SNB",
        "7.62x54mm BT",
        "7.62x54mm PS",
        "7.62x54mm LPS Gzh",
        "7.62x54mm T-46M",
        "12.7x55mm PS12B",
        "12.7x55mm PS12",
        "12.7x55mm PS12A",
        ".338 AP",
        ".338 FMJ",
        ".338 UPZ",
        ".338 Tac-X",
        "12.7x108mm B-32",
        "12.7x108mm BZT-44M"
    )
    val names = mutableListOf(
        "Module-3M",
        "PACA",
        "6B2",
        "MF-UNTAR",
        "Zhuk-3",
        "6B23-1",
        "Kirasa-N",
        "Trooper TFO",
        "Thor Concealable",
        "6B13",
        "6B23-2",
        "Korund-VM",
        "FORT Redut-M",
        "6B13 M",
        "Gen4(High Mobility kit)",
        "Gzhel-K",
        "FORT Defender-2",
        "Gen4(Assault kit)",
        "Gen4(Full protection)",
        "FORT Redut-T5",
        "Hexgrid",
        "Slick",
        "Zhuk-6a",
        "Thor Integrated",
        "6B43",
        "6B5-16",
        "MMAC",
        "6B3TM",
        "6B5-15",
        "M2",
        "M1",
        "AVS",
        "A18",
        "TV-110",
        "Strandhogg",
        "MK4A Assault",
        "Tactec",
        "AACPC",
        "MK4A Protection",
        "MBAV (Tagilla Edition)",
        "Tac-Kek Fast MT",
        "Tank crew",
        "Kolpak-1s",
        "SHPM Firefighter",
        "PSH-97 \"Djeta\"",
        "Jack-o\'-lantern",
        "UNTAR",
        "6B47",
        "LZSh",
        "SSh-68",
        "Kiver-M",
        "DEVTAC Ronin",
        "SSSh-95",
        "TC-2001",
        "TC-2002",
        "TC-800",
        "ACHHC",
        "Zsh-1-2M",
        "ULACH",
        "Bastion",
        "Ops-core Fast MT",
        "Airframe Tan",
        "Team Wendy EXFIL",
        "Galvion Caiman",
        "LSHZ-2DTM",
        "Maska",
        "Altyn",
        "Rys-T",
        "Vulkan-5",
        "Ops-core visor",
        "Caiman Visor",
        "K1S Visor",
        "Multi-hit Ops-Core Visor",
        "Kiver Visor",
        "EXFIL Visor",
        "Zsh-1-2M Visor",
        "LSHZ-2DTM Visor",
        "Vulkan-5 Visor",
        "Altyn Visor",
        "Rys-T Visor",
        "Maska Visor",
        "Ops-Core Mandible",
        "Caiman Mandible",
        "Tac-Kek Trooper Mask",
        "Ops-Core Side Armor",
        "Airframe Ears",
        "EXFIL Ear Cover",
        "Airframe Chops",
        "Caiman Applique",
        "slaap Plate",
        "LSHZ-2DTM Aventail",
        "Additional Armor Bastion"
    )

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_bullet_penetrate, container, false)

        val preferences = context!!.getSharedPreferences("apps", MODE_PRIVATE)
        currentAmmo = preferences.getString("currentAmmo", "5.45x39mm BT")!!
        currentArmor = preferences.getString("currentArmor", "Zhuk-6a")!!
        curAmmo = resources.getStringArray(
            resources.getIdentifier(
                "a${
                    currentAmmo.replace(".", "").replace(" ", "_").replace("mm", "")
                }", "array", context?.packageName
            )
        )
        curArmor = resources.getStringArray(
            resources.getIdentifier(
                "h_${
                    currentArmor.replace("-", "_").replace(" ", "_").lowercase(Locale.ROOT)
                }", "array", context?.packageName
            )
        )

        rootView?.let {
            try {
                Log.d("BPF", "rootView is not null")

                curDur = rootView!!.sb_durability.progress
                maxDur = rootView!!.sb_durability.max

                rootView!!.sb_durability.max = curArmor[3].toInt()
                rootView!!.sb_durability.progress = curArmor[3].toInt()

                rootView!!.cl_bp_ammo.onClick {
                    Log.d("BPF", "Loading Dialog")
                    val builder = AlertDialog.Builder(context)
                    val inflater =
                        LayoutInflater.from(context).inflate(R.layout.dialog_selection, null)
                    builder.setView(inflater)
                        .setNegativeButton("닫기") { _, _ ->

                        }
                    val dialog = builder!!.create()
                    val linearLayout = inflater.findViewById<LinearLayout>(R.id.ll_dialog_selector)
                    val recyclerView = inflater.findViewById<RecyclerView>(R.id.rl_dialog_selector)
                    val searchView = inflater.findViewById<SearchView>(R.id.sv_dialog_selector)
                    searchView.queryHint = "총알 검색하기"
                    var filteredList = namesAmmo
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(p0: String?): Boolean {
                            val target = if (p0.isNullOrEmpty()) {
                                ""
                            } else {
                                p0
                            }
                            linearLayout.removeAllViews()
                            ammoList.clear()
                            filteredList = mutableListOf()
                            for (i in namesAmmo.indices) {
                                if (namesAmmo[i].lowercase(Locale.ROOT)
                                        .contains(target.lowercase(Locale.ROOT))
                                ) {
                                    filteredList.add(namesAmmo[i])
                                }
                            }
                            Log.d("BPF", "Job is completed")
                            for (i in filteredList.indices) {
                                ammoList.add(PenetrateAmmoList(filteredList[i], dialog))
                            }
                            ammoAdapter.notifyDataSetChanged()
                            return false
                        }
                    })
                    Log.d("BPF", "Job is completed")
                    ammoList.clear()
                    for (i in filteredList.indices) {
                        Log.d("BPF", filteredList[i])
                        ammoList.add(PenetrateAmmoList(filteredList[i], dialog))
                    }
                    Log.d("BPF", "${ammoList.size}")
                    ammoAdapter = PenetrateAmmoAdapter(ammoList)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = ammoAdapter
                    dialog.show()
                }
                rootView!!.cl_bp_armor.onClick {
                    Log.d("BPF", "Loading Dialog")
                    val builder = AlertDialog.Builder(context)
                    val inflater = layoutInflater.inflate(R.layout.dialog_selection, null)
                    builder.setView(inflater)
                        .setNegativeButton("닫기") { _, _ -> }
                    val dialog = builder.create()
                    val linearLayout =
                        inflater.findViewById<LinearLayout>(R.id.ll_dialog_selector)
                    val recyclerView = inflater.findViewById<RecyclerView>(R.id.rl_dialog_selector)
                    val searchView = inflater.findViewById<SearchView>(R.id.sv_dialog_selector)
                    searchView.queryHint = "방탄복 검색하기"
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextChange(p0: String?): Boolean {
                            val target = if (p0.isNullOrEmpty()) {
                                ""
                            } else {
                                p0
                            }
                            linearLayout.removeAllViews()
                            armorList.clear()
                            for (i in names.indices) {
                                if (names[i].lowercase(Locale.ROOT)
                                        .contains(target.lowercase(Locale.ROOT))
                                ) {
                                    armorList.add(PenetrateArmorList(names[i], dialog))
                                }
                            }
                            armorAdapter.notifyDataSetChanged()
                            return false
                        }

                        override fun onQueryTextSubmit(p0: String?): Boolean {
                            return false
                        }
                    })
                    armorList.clear()
                    for (i in names.indices) {
                        armorList.add(PenetrateArmorList(names[i], dialog))
                    }
                    armorAdapter = PenetrateArmorAdapter(armorList)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = armorAdapter
                    dialog.show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            rootView!!.sb_durability.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    curDur = p1
                    maxDur = rootView!!.sb_durability.max
                    Log.d("BPF", "$curDur / $maxDur")
                    calculateChance(curArmor, curAmmo, curDur, maxDur)
                    rootView!!.tv_bp_seekbar_text.text = "${p1}/$maxDur"
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })

            refreshImages()

            calculateChance(curArmor, curAmmo, curDur, maxDur)
            val lossDur = durabilityLoss(
                curAmmo[2].toInt(),
                curAmmo[3].replace("%", "").toInt(),
                curArmor[6],
                curArmor[1].toInt()
            )
            rootView!!.tv_bp_durability_loss.text =
                "맞을 시 한발 당 내구도 감소량 : -${round(lossDur * 10) / 10}"
            setChart(curArmor, lossDur, curAmmo[2].toInt())
        }
        return rootView
    }

    private fun refreshImages() {
        val ammos = mutableListOf(
            R.drawable.flechette_12,
            R.drawable.ap_20_12,
            R.drawable.bmg_12,
            R.drawable.poleva6_12,
            R.drawable.ftx_12,
            R.drawable.poleva3_12,
            R.drawable.dual_sabot_12,
            R.drawable.led_12,
            R.drawable.csp_12,
            R.drawable.grizzly_12,
            R.drawable.buck_7_12,
            R.drawable.buck_65_12,
            R.drawable.buck_85_12,
            R.drawable.buck_525_12,
            R.drawable.sf_12,
            R.drawable.rip_12,
            R.drawable.poleva6u_20,
            R.drawable.star_20,
            R.drawable.poleva3_20,
            R.drawable.buck_73_20,
            R.drawable.buck_75_20,
            R.drawable.buck_62_20,
            R.drawable.buck_56_20,
            R.drawable.devastator_20,
            R.drawable.shrapnel_25_2375,
            R.drawable.shrapnel_10_2375,
            R.drawable.barricade_23,
            R.drawable.pmb_18,
            R.drawable.pmm_18,
            R.drawable.bzt_gzh_18,
            R.drawable.rg028_gzh_18,
            R.drawable.pst_gzh_18,
            R.drawable.ppt_gzh_18,
            R.drawable.ppe_gzh_18,
            R.drawable.prs_gs_18,
            R.drawable.ps_gs_ppo_18,
            R.drawable.pso_gzh_18,
            R.drawable.p_gzh_18,
            R.drawable.psv_18,
            R.drawable.sp7_gzh_18,
            R.drawable.sp8_gzh_18,
            R.drawable.n7n31_19,
            R.drawable.ap_19,
            R.drawable.pst_19,
            R.drawable.gt_19,
            R.drawable.luger_19,
            R.drawable.pso_19,
            R.drawable.quakemaker_19,
            R.drawable.rip_19,
            R.drawable.tt_lrnpc,
            R.drawable.tt_lrn,
            R.drawable.tt_fmj43,
            R.drawable.tt_akbs,
            R.drawable.tt_pgl,
            R.drawable.tt_pt_gzh,
            R.drawable.tt_pst_gzh,
            R.drawable.ap_45,
            R.drawable.fmj_45,
            R.drawable.lasermatch_45,
            R.drawable.hydrashok_45,
            R.drawable.rip_45,
            R.drawable.sp13_21,
            R.drawable.sp10_21,
            R.drawable.sp11_21,
            R.drawable.sp12_21,
            R.drawable.r37f_57,
            R.drawable.ss198lf_57,
            R.drawable.r37x_57,
            R.drawable.ss197sr_57,
            R.drawable.l191_57,
            R.drawable.sb193_57,
            R.drawable.ss190_57,
            R.drawable.ap_46,
            R.drawable.fmj_46,
            R.drawable.subsonic_46,
            R.drawable.action_46,
            R.drawable.bp_939,
            R.drawable.spp_939,
            R.drawable.pab9_939,
            R.drawable.sp6_939,
            R.drawable.sp5_939,
            R.drawable.ap_366,
            R.drawable.eko_366,
            R.drawable.fmj_366,
            R.drawable.geksa_366,
            R.drawable.igolnik_545,
            R.drawable.bs_545,
            R.drawable.bt_545,
            R.drawable.bp_545,
            R.drawable.pp_545,
            R.drawable.ps_545,
            R.drawable.t_545,
            R.drawable.fmj_545,
            R.drawable.us_545,
            R.drawable.prs_545,
            R.drawable.hp_545,
            R.drawable.sp_545,
            R.drawable.ssa_ap_556,
            R.drawable.m995_556,
            R.drawable.m855a1_556,
            R.drawable.m856a1_556,
            R.drawable.m855_556,
            R.drawable.fmj_556,
            R.drawable.m856_556,
            R.drawable.mk_318_556,
            R.drawable.mk255_556,
            R.drawable.hp_556,
            R.drawable.warmage_556,
            R.drawable.mai_ap_739,
            R.drawable.bp_739,
            R.drawable.ps_739,
            R.drawable.t45m_739,
            R.drawable.us_739,
            R.drawable.hp_739,
            R.drawable.whisper_300,
            R.drawable.vmax_300,
            R.drawable.bpz_300,
            R.drawable.m62_300,
            R.drawable.ap_300,
            R.drawable.m933_751,
            R.drawable.m61_751,
            R.drawable.m62_751,
            R.drawable.m80_751,
            R.drawable.bpz_fmj_751,
            R.drawable.tpz_sp_751,
            R.drawable.ultra_nosier_751,
            R.drawable.r54_bs,
            R.drawable.r54_snb,
            R.drawable.r54_7bt1,
            R.drawable.r54_7n1,
            R.drawable.r54_lps_gzh,
            R.drawable.r54_t46m,
            R.drawable.ps12b_127,
            R.drawable.ps12_127,
            R.drawable.ps12a_127,
            R.drawable.ap_338,
            R.drawable.fmj_338,
            R.drawable.upz_338,
            R.drawable.tac_x_338,
            R.drawable.b_32_108,
            R.drawable.bzt_44m_108
        )

        val armors = mutableListOf(
            R.drawable.a_3m,
            R.drawable.paca,
            R.drawable.a_6b2,
            R.drawable.untar,
            R.drawable.zhuk_3,
            R.drawable.a_6b23_1icon,
            R.drawable.kirasa,
            R.drawable.trooper,
            R.drawable.thor_concealable,
            R.raw.a_6b13,
            R.drawable.a_6b23_2,
            R.drawable.korund,
            R.drawable.redut_m,
            R.drawable.a_6b13m,
            R.drawable.gen4_hmk,
            R.drawable.gzhel_k,
            R.drawable.defender_2,
            R.drawable.gen4_assault,
            R.drawable.gen4_full,
            R.drawable.redut_t5,
            R.drawable.hexgrid,
            R.raw.a_slick,
            R.drawable.zhuk_6a,
            R.drawable.thor_integrated,
            R.drawable.zabralo,
            R.drawable.a_6b5_16,
            R.drawable.a_mmac,
            R.drawable.a_6b3tm,
            R.drawable.a_6b5_15,
            R.drawable.m2,
            R.drawable.m1,
            R.drawable.avs,
            R.drawable.a18,
            R.drawable.tv_110,
            R.drawable.a_strandhogg,
            R.drawable.mk4a_assault,
            R.drawable.tactec,
            R.drawable.aacpc,
            R.drawable.mk4a_protection,
            R.drawable.avs_mbav,
            R.drawable.tk_fast_mt,
            R.drawable.tank_crew,
            R.drawable.kolpak_1s,
            R.drawable.firefighter,
            R.drawable.djeta,
            R.drawable.pumpkin,
            R.drawable.untar_helmet,
            R.raw.h_6b47,
            R.drawable.lzsh,
            R.drawable.ssh_68,
            R.drawable.kiver_m,
            R.drawable.ronin,
            R.drawable.sssh_95,
            R.drawable.tc_2001,
            R.drawable.tc_2002,
            R.drawable.tc_800,
            R.raw.achhc,
            R.raw.zsh_1_2m,
            R.raw.ulach,
            R.drawable.bastion,
            R.raw.fast_mt,
            R.drawable.airframe,
            R.raw.exfil,
            R.drawable.caiman,
            R.drawable.lshz_2dtm,
            R.raw.maska,
            R.drawable.altyn,
            R.drawable.rys_t,
            R.drawable.vulkan_5,
            R.drawable.visor_ops_core_small,
            R.drawable.visior_caiman,
            R.drawable.visor_k1s,
            R.drawable.visor_ops_core,
            R.drawable.visor_kiver,
            R.raw.visor_exfil,
            R.drawable.visor_zsh_1_2m,
            R.drawable.visor_lshz_2dtm,
            R.drawable.visor_vulkan_5,
            R.drawable.visor_alytn,
            R.drawable.visior_rys_t,
            R.raw.visor_maska,
            R.drawable.attachment_chop_ops_core,
            R.drawable.attachment_chop_caiman,
            R.drawable.attachment_trooper,
            R.drawable.attachment_ops_core,
            R.drawable.attachment_airframe,
            R.raw.attachment_exfil,
            R.drawable.attachment_chop_airframe,
            R.drawable.attachment_caiman,
            R.drawable.attachment_slaap,
            R.drawable.attachment_lshz_2dtm,
            R.drawable.attachment_bastion
        )

        rootView!!.tv_bp_ammo_name.text = curAmmo[0]
        rootView!!.tv_bp_armor_name.text = curArmor[0]
        rootView!!.tv_bp_seekbar_text.text = "${curArmor[3]}/${curArmor[3]}"
        val scope = CoroutineScope(IO)
        scope.launch {
            for (i in names.indices) {
                if (names[i] == currentArmor) {
                    runOnUiThread {
                        loadWithWebp(context!!, rootView!!.iv_bp_armor, armors[i])
                    }
                }
            }
            for (i in namesAmmo.indices) {
                if (namesAmmo[i] == currentAmmo) {
                    runOnUiThread {
                        loadWithWebp(context!!, rootView!!.iv_bp_ammo, ammos[i])
                    }
                }
            }
        }
    }

    private fun setChart(curArmor: Array<String>, durLoss: Double, bulletPen: Int) {
        val xAxis = rootView!!.lc_bp_penetrate_chance.xAxis

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textColor = context?.getColor(R.color.text_color)!!
            textSize = 10f
            setDrawGridLines(false)
            granularity = 1f
            axisMinimum = 1f
            isGranularityEnabled = true
        }
        rootView!!.lc_bp_penetrate_chance.apply {
            axisLeft.axisMaximum = 100f
            axisRight.isEnabled = false
            legend.apply {
                textColor = context?.getColor(R.color.text_color)!!
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }
        }
        val lineData = LineData()
        rootView!!.lc_bp_penetrate_chance.data = lineData

        drawChart(curArmor, durLoss, bulletPen)
    }

    private fun drawChart(curArmor: Array<String>, durLoss: Double, bulletPen: Int) {
        if (thread != null) {
            thread!!.interrupt()
            thread = null
        }

        var dur = rootView!!.sb_durability.progress.toDouble()

        CoroutineScope(Main).launch {
            while (dur >= 0.0) {
                val chance =
                    if (dur != 0.0) {
                        penetrateChance(
                            dur.toInt(),
                            rootView!!.sb_durability.max,
                            curArmor[1].toInt(),
                            bulletPen
                        )
                    } else {
                        100.0
                    }
                Log.d("BPF", "current chance is $chance and durability is $dur")
                val data = rootView!!.lc_bp_penetrate_chance.data
                data?.let {
                    var set: ILineDataSet? = data.getDataSetByIndex(0)
                    if (set == null) {
                        set = LineDataSet(null, "관통 확률 (%)")
                            .apply {
                                axisDependency = YAxis.AxisDependency.LEFT
                                color = context!!.getColor(R.color.text_color)
                                setCircleColor(context!!.getColor(R.color.very_effective))
                                valueTextSize = 12f
                                lineWidth = 2f
                                circleRadius = 3f
                                fillAlpha = 0
                                fillColor = context!!.getColor(R.color.ignore)
                                highLightColor = context!!.getColor(R.color.text_color)
                                valueTextColor = context!!.getColor(R.color.text_color)
                                setDrawValues(true)
                            }
                        data.addDataSet(set)
                    }
                    data.addEntry(Entry(set.entryCount.toFloat() + 1, chance.toFloat()), 0)
                    data.notifyDataChanged()
                    rootView!!.lc_bp_penetrate_chance.apply {
                        notifyDataSetChanged()
                        setVisibleXRangeMaximum(rootView!!.sb_durability.max.toFloat())
                        setPinchZoom(true)
                        isDoubleTapToZoomEnabled = false
                        description.text = "맞은 횟수"
                        backgroundColor = R.color.background
                        description.textColor = context!!.getColor(R.color.text_color)
                        description.textSize = 15f
                    }
                }
                if (dur <= durLoss && dur > 0) {
                    dur = 0.0
                    Log.d("BPF", "dur is too low")
                } else {
                    dur -= durLoss
                }
            }
        }
    }

    private fun calculateChance(
        curArmor: Array<String>,
        curAmmo: Array<String>,
        curDur: Int,
        maxDur: Int
    ) {

        val chance = penetrateChance(
            curDur,
            maxDur,
            curArmor[1].toInt(),
            curAmmo[2].replace("%", "").toInt()
        )

        Log.d("BPF", "$chance %")

        rootView!!.tv_bp_penetrate_chance.text = "${floor(chance * 10) / 10}%"
        convertColor(chance)
    }

    private fun convertColor(chance: Double) {
        when {
            chance > 90 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.ignore)
            }
            chance > 74 -> {
                rootView!!.tv_bp_penetrate_chance.textColor =
                    context!!.getColor(R.color.very_effective)
            }
            chance > 58 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.effective)
            }
            chance > 42 -> {
                rootView!!.tv_bp_penetrate_chance.textColor =
                    context!!.getColor(R.color.slight_effective)
            }
            chance > 26 -> {
                rootView!!.tv_bp_penetrate_chance.textColor =
                    context!!.getColor(R.color.magdump_only)
            }
            chance > 10 -> {
                rootView!!.tv_bp_penetrate_chance.textColor =
                    context!!.getColor(R.color.possible_but)
            }
            else -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.pointless)
            }
        }
    }

    private fun penetrateChance(curDur: Int, maxDur: Int, armClass: Int, bulletPen: Int): Double {

        if (curDur == 0) {
            return 100.0
        }

        val armPer: Double = (curDur.toDouble() / maxDur.toDouble()) * 100.0
        Log.d("BPF", "$armPer , $curDur , $maxDur")

        val armorClass = armClass * 10
        val stopPwr = (121.0 - (5000.0 / (45.0 + (armPer * 2.0)))) * armorClass * 0.01
        var penChance = 0.0

        Log.d("BPF", "stopPwr is $stopPwr")

        when {
            stopPwr >= bulletPen + 15 -> {
                Log.d("BPF", "Cant penetrate")
            }
            stopPwr >= bulletPen -> {
                penChance = 0.4 * ((stopPwr - bulletPen) - 15.0).pow(2.0)
                Log.d("BPF", "${stopPwr - bulletPen}")
                Log.d("BPF", "Can penetrate by $penChance %")
            }
            else -> {
                penChance = (bulletPen / ((0.9 * stopPwr) - bulletPen)) + 100
                Log.d("BPF", "Pen Chance 2 penetrate by $penChance")
            }
        }
        return penChance
    }

    private fun durabilityLoss(
        bulletPen: Int,
        bulletDmg: Int,
        armorMaterial: String,
        armClass: Int
    ): Double {
        val armorClass = armClass * 10.0
        val durMod = bulletPen / armorClass
        val bulletArmorDmg = bulletDmg / 100.0
        val armorFragility = armFragile(armorMaterial)
        val loss = bulletPen * bulletArmorDmg * armorFragility * durMod

        return if (loss <= 1.0) {
            1.0
        } else {
            bulletPen * bulletArmorDmg * armorFragility * durMod
        }
    }

    private fun armFragile(material: String): Double {
        when (material) {
            "아라미드(Aramid)" -> return 0.25
            "합성 재료(Combined Materials)" -> return 0.5
            "울트라폴리에틸렌(Ultrahighweight polyethylene)" -> return 0.45
            "알루미늄(Aluminium)" -> return 0.6
            "강철(Armor Steel)" -> return 0.7
            "유리(Glass)" -> return 0.8
            "세라믹(Ceramic)" -> return 0.8
            "티타늄(Titan)" -> return 0.55
        }
        return 1.0
    }

    inner class PenetrateArmorList(val name: String, val dialog: AlertDialog)

    inner class PenetrateArmorAdapter(private val items: ArrayList<PenetrateArmorList>) :
        RecyclerView.Adapter<PenetrateArmorAdapter.ViewHolder>() {
        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: PenetrateArmorAdapter.ViewHolder, position: Int) {
            val item = items[position]
            val listener = View.OnClickListener {
                currentArmor = item.name
                curArmor = resources.getStringArray(
                    resources.getIdentifier(
                        "h_${
                            item.name.replace("-", "_")
                                .replace(" ", "_").replace("(", "")
                                .replace(")", "").replace("\"", "")
                                .replace("\'", "").lowercase(Locale.ROOT)
                        }", "array", context?.packageName
                    )
                )
                rootView!!.sb_durability.max = curArmor[3].toInt()
                rootView!!.sb_durability.progress = curArmor[3].toInt()
                maxDur = rootView!!.sb_durability.max
                refreshImages()
                calculateChance(curArmor, curAmmo, curDur, maxDur)
                val lossDur = durabilityLoss(
                    curAmmo[2].toInt(),
                    curAmmo[3].replace("%", "").toInt(),
                    curArmor[6],
                    curArmor[1].toInt()
                )
                rootView!!.tv_bp_durability_loss.text =
                    "-${floor(lossDur * 10) / 10}"
                setChart(curArmor, lossDur, curAmmo[2].toInt())
                rootView!!.lc_bp_penetrate_chance.data.notifyDataChanged()
                item.dialog.dismiss()
            }
            holder.apply {
                try {
                    val data = resources.getStringArray(
                        resources.getIdentifier(
                            "h_${
                                item.name.replace(
                                    "-",
                                    "_"
                                ).replace(" ", "_").replace("(", "")
                                    .replace(")", "").replace("\"", "")
                                    .replace("\'", "").lowercase(Locale.ROOT)
                            }", "array", context?.packageName
                        )
                    )
                    bind(listener, data)
                }catch (e: Resources.NotFoundException){
                    Log.e("BPF", item.name.replace(
                        "-",
                        "_"
                    ).replace(" ", "_").replace("(", "")
                        .replace(")", "").replace("\"", "")
                        .replace("\'", "").lowercase(Locale.ROOT))
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): PenetrateArmorAdapter.ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_armor_selection, parent, false)
            return ViewHolder(inflatedView)
        }

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private var view = v
            fun bind(listener: View.OnClickListener, data: Array<String>) {
                view.setOnClickListener(listener)
                view.tv_menu_armor_name.text = data[0]
                view.tv_menu_armor_class.text = data[1]
                view.tv_menu_armor_durability.text = data[3]
            }
        }
    }

    inner class PenetrateAmmoList(val name: String, val dialog: AlertDialog)

    inner class PenetrateAmmoAdapter(private val items: ArrayList<PenetrateAmmoList>) :
        RecyclerView.Adapter<PenetrateAmmoAdapter.ViewHolder>() {

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            val listener = View.OnClickListener {
                currentAmmo = item.name
                curAmmo = resources.getStringArray(
                    resources.getIdentifier(
                        "a${
                            item.name.replace(
                                ".",
                                ""
                            ).replace("-", "_").replace(" ", "_")
                                .replace("\"", "").replace("/", "")
                                .replace("`mm", "``")
                                .replace("mm", "").replace("``", "mm")
                        }", "array", context?.packageName
                    )
                )
                refreshImages()
                calculateChance(curArmor, curAmmo, curDur, maxDur)
                val lossDur = durabilityLoss(
                    curAmmo[2].toInt(),
                    curAmmo[3].replace("%", "").toInt(),
                    curArmor[6],
                    curArmor[1].toInt()
                )
                rootView!!.tv_bp_durability_loss.text =
                    "-${floor(lossDur * 10) / 10}"
                setChart(curArmor, lossDur, curAmmo[2].toInt())
                rootView!!.lc_bp_penetrate_chance.data.notifyDataChanged()
                item.dialog.dismiss()
                rootView!!.lc_bp_penetrate_chance.data.notifyDataChanged()
            }
            holder.apply {
                Log.d("BPF", item.name)
                val data = resources.getStringArray(
                    resources.getIdentifier(
                        "a${
                            item.name.replace(
                                ".",
                                ""
                            ).replace("-", "_").replace(" ", "_")
                                .replace("\"", "").replace("/", "")
                                .replace("`mm", "``")
                                .replace("mm", "").replace("``", "mm")
                        }", "array", context?.packageName
                    )
                )
                bind(listener, data)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_armor_selection, parent, false)
            return ViewHolder(inflatedView)
        }

        inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            private var view: View = v
            fun bind(listener: View.OnClickListener, data: Array<String>) {
                view.tv_menu_armor_name.text = data[0]
                view.tv_menu_armor_durability.text = data[1]
                view.tv_menu_armor_class.text = data[2]
                view.setOnClickListener(listener)
            }
        }
    }

}