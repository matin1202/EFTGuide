
package com.matin.eftguide.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.TextView
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
import kotlinx.android.synthetic.main.fragment_bullet_penetrate.view.*
import kotlinx.android.synthetic.main.menu_armor_selection.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onSeekBarChangeListener
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.round

@Suppress("NAME_SHADOWING")
@SuppressLint("SetTextI18n")
class BulletPenetrateFragment : Fragment() {

    private var currentArmor = "Zhuk-6a"
    private var currentAmmo = "5.45x39mm BT"
    private var thread: Thread? = null
    private lateinit var curAmmo: Array<String>
    private lateinit var curArmor: Array<String>
    var maxDur: Int = 0
    var curDur: Int = 0

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_bullet_penetrate, container, false)

        curAmmo = resources.getStringArray(resources.getIdentifier("a${currentAmmo.replace(".", "").replace(" ", "_").replace("mm", "")}", "array", context?.packageName))
        curArmor = resources.getStringArray(resources.getIdentifier("h_${currentArmor.replace("-", "_").replace(" ", "_").toLowerCase(Locale.ROOT)}", "array", context?.packageName))

        rootView?.let {
            Log.d("BPF", "rootView is not null")

            curDur = rootView!!.sb_durability.progress
            maxDur = rootView!!.sb_durability.max

            rootView!!.sb_durability.max = curArmor[3].toInt()
            rootView!!.sb_durability.progress = curArmor[3].toInt()

            rootView!!.cl_bp_armor.onClick {
                val builder = AlertDialog.Builder(context)
                val inflater = layoutInflater.inflate(R.layout.dialog_selection, null)
                builder.setView(inflater)
                    .setNegativeButton("닫기"){_, _ ->

                    }
                val dialog = builder.create()
                val linearLayout = inflater.findViewById<LinearLayout>(R.id.ll_dialog_selector)
                val searchView = inflater.findViewById<SearchView>(R.id.sv_dialog_selector)
                searchView.queryHint = "방탄복 검색하기"

                val names = mutableListOf("Module-3M", "PACA", "6B2", "MF-UNTAR", "Zhuk-3", "6B23-1", "Kirasa-N", "Trooper TFO", "6B13", "6B23-2", "Korund-VM", "FORT Redut-M", "6B13 M", "Gen4(High Mobility kit)", "Gzhel-K", "FORT Defender-2", "Gen4(Assault kit)", "Gen4(Full protection)", "FORT Redut-T5", "Hexgrid", "Slick", "Zhuk-6a", "6B43", "Tac-Kek Fast MT", "Tank crew", "Kolpak-1s", "SHPM Firefighter", "PSH-97 \"Djeta\"", "Jack-o\'-lantern", "UNTAR", "6B47", "LZSh", "SSh-68", "Kiver-M", "DEVTAC Ronin", "SSSh-95", "TC-2001", "TC-2002", "TC-800", "ACHHC", "Zsh-1-2M", "ULACH", "Bastion", "Ops-core Fast MT", "Airframe Tan", "Team Wendy EXFIL", "Galvion Caiman", "LSHZ-2DTM", "Maska", "Altyn", "Rys-T", "Vulkan-5", "Ops-core visor", "Caiman Visor", "K1S Visor", "Multi-hit Ops-Core Visor", "Kiver Visor", "EXFIL Visor", "Zsh-1-2M Visor", "LSHZ-2DTM Visor", "Vulkan-5 Visor", "Altyn Visor", "Rys-T Visor", "Maska Visor", "Ops-Core Mandible", "Caiman Mandible", "Tac-Kek Trooper Mask", "Ops-Core Side Armor", "Airframe Ears", "EXFIL Ear Cover", "Airframe Chops", "Caiman Applique", "slaap Plate", "LSHZ-2DTM Aventail", "Additional Armor Bastion")
                var filteredList = names
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextChange(p0: String?): Boolean {
                        val target = if(p0.isNullOrEmpty()){ "" } else{ p0 }
                        linearLayout.removeAllViews()
                        filteredList = ArrayList<String>()
                            for(i in names.indices) {
                                if (names[i].contains(target)) {
                                    filteredList.add(names[i])
                                }
                            }


                            for(i in filteredList.indices) {
                                val layoutInflater = LayoutInflater.from(context).inflate(R.layout.menu_armor_selection, null)
                                val thisArmor = resources.getStringArray(resources.getIdentifier("h_${filteredList[i].replace("-", "_").replace(" ", "_").replace("(", "").replace(")", "").replace("\"", "").replace("\'", "").toLowerCase(Locale.ROOT)}", "array", context?.packageName))
                                layoutInflater.tv_menu_armor_name.text = thisArmor[0]
                                layoutInflater.tv_menu_armor_class.text = thisArmor[1]
                                layoutInflater.tv_menu_armor_durability.text = thisArmor[3]
                                layoutInflater.onClick {
                                    currentArmor = filteredList[i]
                                    curArmor = resources.getStringArray(resources.getIdentifier("h_${currentArmor.replace("-", "_").replace(" ", "_").replace("(", "").replace(")", "").replace("\"", "").replace("\"", "").replace("\'", "").toLowerCase(Locale.ROOT)}", "array", context?.packageName))
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
                                    rootView!!.sb_durability.max = curArmor[3].toInt()
                                    rootView!!.sb_durability.progress = curArmor[3].toInt()
                                    maxDur = rootView!!.sb_durability.max
                                    dialog.dismiss()
                                }
                                linearLayout.addView(layoutInflater)
                            }

                        return false
                    }

                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }
                })
                for(i in filteredList.indices){
                    val layoutInflater = LayoutInflater.from(context).inflate(R.layout.menu_armor_selection, null)
                    val thisArmor = resources.getStringArray(resources.getIdentifier("h_${filteredList[i].replace("-", "_").replace(" ", "_").replace("(", "").replace(")", "").replace("\"", "").replace("\'", "").toLowerCase(Locale.ROOT)}", "array", context?.packageName))
                    layoutInflater.tv_menu_armor_name.text = thisArmor[0]
                    layoutInflater.tv_menu_armor_class.text = thisArmor[1]
                    layoutInflater.tv_menu_armor_durability.text = thisArmor[3]
                    layoutInflater.onClick{
                        currentArmor = filteredList[i]
                        curArmor = resources.getStringArray(resources.getIdentifier("h_${currentArmor.replace("-", "_").replace(" ", "_").replace("(", "").replace(")", "").replace("\"", "").replace("\'", "").toLowerCase(Locale.ROOT)}", "array", context?.packageName))
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
                        rootView!!.sb_durability.max = curArmor[3].toInt()
                        rootView!!.sb_durability.progress = curArmor[3].toInt()
                        maxDur = rootView!!.sb_durability.max
                        dialog.dismiss()
                    }
                    linearLayout.addView(layoutInflater)
                }


                 dialog.show()
            }


            rootView!!.sb_durability.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
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
            rootView!!.tv_bp_durability_loss.text = "맞을 시 한발 당 내구도 감소량 : -${round(lossDur*10) /10}"
            setChart(curArmor, lossDur, curAmmo[2].toInt())
        }
        return rootView
    }

    private fun refreshImages(){
        val armors = mutableListOf(R.drawable.a_3m, R.drawable.paca, R.drawable.a_6b2, R.drawable.untar, R.drawable.zhuk_3, R.drawable.a_6b23_1icon, R.drawable.kirasa, R.drawable.trooper, R.raw.a_6b13, R.drawable.a_6b23_2, R.drawable.korund, R.drawable.redut_m, R.drawable.a_6b13m, R.drawable.gen4_hmk, R.drawable.gzhel_k, R.drawable.defender_2, R.drawable.gen4_assault, R.drawable.gen4_full, R.drawable.redut_t5, R.drawable.hexgrid, R.raw.a_slick, R.drawable.zhuk_6a, R.drawable.zabralo, R.drawable.tk_fast_mt, R.drawable.tank_crew, R.drawable.kolpak_1s, R.drawable.firefighter, R.drawable.djeta, R.drawable.pumpkin, R.drawable.untar_helmet, R.raw.h_6b47, R.drawable.lzsh, R.drawable.ssh_68, R.drawable.kiver_m, R.drawable.ronin, R.drawable.sssh_95, R.drawable.tc_2001, R.drawable.tc_2002, R.drawable.tc_800, R.raw.achhc, R.raw.zsh_1_2m, R.raw.ulach, R.drawable.bastion, R.raw.fast_mt, R.drawable.airframe, R.raw.exfil, R.drawable.caiman, R.drawable.lshz_2dtm, R.raw.maska, R.drawable.altyn, R.drawable.rys_t, R.drawable.vulkan_5, R.drawable.visor_ops_core_small, R.drawable.visior_caiman, R.drawable.visor_k1s, R.drawable.visor_ops_core, R.drawable.visor_kiver, R.raw.visor_exfil, R.drawable.visor_zsh_1_2m, R.drawable.visor_lshz_2dtm, R.drawable.visor_vulkan_5, R.drawable.visor_alytn, R.drawable.visior_rys_t, R.raw.visor_maska, R.drawable.attachment_chop_ops_core, R.drawable.attachment_chop_caiman, R.drawable.attachment_trooper, R.drawable.attachment_ops_core, R.drawable.attachment_airframe,
            R.raw.attachment_exfil, R.drawable.attachment_chop_airframe, R.drawable.attachment_caiman, R.drawable.attachment_slaap,  R.drawable.attachment_lshz_2dtm, R.drawable.attachment_bastion)
        val names = mutableListOf("Module-3M", "PACA", "6B2", "MF-UNTAR", "Zhuk-3", "6B23-1", "Kirasa-N", "Trooper TFO", "6B13", "6B23-2", "Korund-VM", "FORT Redut-M", "6B13 M", "Gen4(High Mobility kit)", "Gzhel-K", "FORT Defender-2", "Gen4(Assault kit)", "Gen4(Full protection)", "FORT Redut-T5", "Hexgrid", "Slick", "Zhuk-6a", "6B43", "Tac-Kek Fast MT", "Tank crew", "Kolpak-1s", "SHPM Firefighter", "PSH-97 \"Djeta\"", "Jack-o\'-lantern", "UNTAR", "6B47", "LZSh", "SSh-68", "Kiver-M", "DEVTAC Ronin", "SSSh-95", "TC-2001", "TC-2002", "TC-800", "ACHHC", "Zsh-1-2M", "ULACH", "Bastion", "Ops-core Fast MT", "Airframe Tan", "Team Wendy EXFIL", "Galvion Caiman", "LSHZ-2DTM", "Maska", "Altyn", "Rys-T", "Vulkan-5", "Ops-core visor", "Caiman Visor", "K1S Visor", "Multi-hit Ops-Core Visor", "Kiver Visor", "EXFIL Visor", "Zsh-1-2M Visor", "LSHZ-2DTM Visor", "Vulkan-5 Visor", "Altyn Visor", "Rys-T Visor", "Maska Visor", "Ops-Core Mandible", "Caiman Mandible", "Tac-Kek Trooper Mask", "Ops-Core Side Armor", "Airframe Ears", "EXFIL Ear Cover", "Airframe Chops", "Caiman Applique", "slaap Plate", "LSHZ-2DTM Aventail", "Additional Armor Bastion")
        rootView!!.tv_bp_ammo_name.text = curAmmo[0]
        rootView!!.tv_bp_armor_name.text = curArmor[0]
        rootView!!.tv_bp_seekbar_text.text = "${curArmor[3]}/${curArmor[3]}"
        for(i in names.indices){
            if(names[i] == currentArmor){
                rootView!!.iv_bp_armor.imageResource = armors[i]
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
                    if(dur!=0.0){penetrateChance(dur.toInt(), rootView!!.sb_durability.max, curArmor[1].toInt(), bulletPen)}else{100.0}
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
                    data.addEntry(Entry(set.entryCount.toFloat(), chance.toFloat()), 0)
                    data.notifyDataChanged()
                    rootView!!.lc_bp_penetrate_chance.apply {
                        notifyDataSetChanged()
                        setVisibleXRangeMaximum(rootView!!.sb_durability.max.toFloat())
                        setPinchZoom(true)
                        isDoubleTapToZoomEnabled = true
                        description.text = "맞은 횟수"
                        backgroundColor = R.color.background
                        description.textColor = R.color.text_color
                        description.textSize = 15f
                    }
                }
                if(dur<durLoss&&dur > 0){
                    dur = 0.0
                }
                dur -= durLoss
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

        rootView!!.tv_bp_penetrate_chance.text = "${floor(chance*10)/10}%"
        convertColor(chance)
    }

    private fun convertColor(chance: Double) {
        when {
            chance > 90 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.ignore)
            }
            chance > 74 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.very_effective)
            }
            chance > 58 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.effective)
            }
            chance > 42 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.slight_effective)
            }
            chance > 26 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.magdump_only)
            }
            chance > 10 -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.possible_but)
            }
            else -> {
                rootView!!.tv_bp_penetrate_chance.textColor = context!!.getColor(R.color.pointless)
            }
        }
    }

    private fun penetrateChance(curDur: Int, maxDur: Int, armClass: Int, bulletPen: Int): Double {

        if(curDur==0){
            return 100.0
        }

        val armPer: Double = (curDur.toDouble()/maxDur.toDouble()) * 100.0
        Log.d("BPF", "$armPer , $curDur , $maxDur")

        val armorClass = armClass * 10
        val stopPwr = (121.0 - (5000.0 / (45.0 + (armPer * 2.0)))) * armorClass * 0.01
        var penChance = 0.0

        Log.d("BPF", "stopPwr is $stopPwr")

        when {
            stopPwr >= bulletPen + 15 -> {
                Log.d("BPF" ,"Cant penetrate")
            }
            stopPwr >= bulletPen -> {
                penChance = 0.4 * ((stopPwr - bulletPen) - 15.0).pow(2.0)
                Log.d("BPF", "${stopPwr - bulletPen}")
                Log.d("BPF", "Can penetrate by $penChance %")
            }
            else -> {
                penChance = (bulletPen  / ((0.9 * stopPwr) - bulletPen)) + 100
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

        return bulletPen * bulletArmorDmg * armorFragility * durMod
    }

    private fun armFragile(material: String): Double {
        when (material) {
            "아라미드(Aramid)" -> return 0.25
            "합성 재료(Combined Materials)" -> return 0.5
            "울트라폴리에틸렌(Ultrahighweight Polyethylene)" -> return 0.45
            "알루미늄(Aluminium)" -> return 0.6
            "강철(Armor Steel)" -> return 0.7
            "유리(Glass)" -> return 0.8
            "세라믹(Ceramic)" -> return 0.8
            "티타늄(Titan)" -> return 0.55
        }
        return 1.0
    }

}