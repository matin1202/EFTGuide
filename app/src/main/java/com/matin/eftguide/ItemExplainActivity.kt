package com.matin.eftguide

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import com.google.firebase.analytics.FirebaseAnalytics
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.base.loadWithWebp
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_item_explain.*
import kotlinx.android.synthetic.main.ammo_list.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.*
import java.util.regex.Pattern
import android.text.util.Linkify

class ItemExplainActivity : BaseActivity() {
    @SuppressLint("SetTextI18n")
    var mods = arrayListOf<String>()
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_explain)



        if(prefs.getBoolean("adLoad", true)) {
            iea_adView.loadAd(AdLoaderClass().adRequest)
        }

        try {

            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
            val items = intent.getStringExtra("type")!!.replace(" ", "_").replace("\"", "")
                .replace("\'", "").replace(".", "").replace("-", "_").replace("/", "")
                .replace("(", "").replace(")", "").split("||")
            val extra: String = items[1]
            val item = items[0].toLowerCase(Locale.ROOT)
            Log.d("IEA", item)
            val mBundle = Bundle()
            mBundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item)
            mBundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "IEA")
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, mBundle)

            val buffer: List<String>?
            Log.d("IEA", item)
            val resId = resources.getIdentifier(item, "array", packageName)

            val stringArray = resources.getStringArray(resId)
            var text = ""
            when (extra) {
                "heal" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.remove),
                        getString(R.string.add),
                        getString(R.string.energy),
                        getString(R.string.hydration),
                        getString(R.string.item_using_time),
                        getString(R.string.item_uses),
                        getString(R.string.item_pool),
                        getString(R.string.item_max_heal)
                    )
                    for (i in buffer.indices) {
                        if (i == 1 || i == 2 || i == 3) {
                            var string = stringArray[i]
                            string = buffer[i] + "<br/>" + string
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Toxin]",
                                    "  <img src=\"[Toxin]\" alt='Toxin'>"
                                )
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>") + "<br/>"
                            text += string
                            continue
                        }

                        text += "${buffer[i]} : ${stringArray[i]}<br/>"

                    }
                }
                "stimulator" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.buff),
                        getString(R.string.debuff),
                        getString(R.string.item_using_time)
                    )
                    for (i in buffer.indices) {
                        if (i == 1 || i == 2) {
                            val string = buffer[i] + "<br/>" + stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Toxin]",
                                    "  <img src=\"[Toxin]\" alt='Toxin'>"
                                )
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>") + "<br/>"
                            Log.d("IEA", "${string.indexOf("{B")}")
                            text += string
                            continue
                        }
                        text += "${buffer[i]} : ${stringArray[i]}<br/>"
                    }
                }
                "provision" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.energy),
                        getString(R.string.hydration),
                        getString(R.string.uses),
                        getString(R.string.buff),
                        getString(R.string.debuff)
                    )
                    for (i in buffer.indices) {
                        if (i == 4 || i == 5) {
                            val string = buffer[i] + "<br/>" + stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>") + "<br/>"
                            text += string
                            continue
                        }
                        text += "${buffer[i]} : ${
                            stringArray[i].replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "weapon" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.vertical_recoil),
                        getString(R.string.horizontal_recoil),
                        getString(R.string.ergonomics),
                        getString(R.string.effect_range),
                        getString(R.string.using_ammo),
                        getString(R.string.rpm),
                        getString(R.string.fire_mod),
                        getString(R.string.min_recoil),
                        getString(R.string.max_ergo),
                        getString(R.string.budget_modding),
                        getString(R.string.best_suppr),
                        getString(R.string.budget_ammo),
                        getString(R.string.best_ammo),
                        getString(R.string.note),
                        "아이템 설명"
                    )
                    for (i in buffer.indices) {
                        if (i == 8 || i == 9 || i == 10) {
                            Log.d("IEA", "d" + stringArray[i])
                            if (stringArray[i] == "") continue
                            text += "${buffer[i]} : ${buffer[i]} 바로가기<br/>"
                            mods.add(stringArray[i])
                            continue
                        }
                        if(i == 15) continue

                        text += "${buffer[i]} : ${
                            stringArray[i].replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "meleeweapon" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.chop_dmg),
                        getString(R.string.chop_range),
                        getString(R.string.stab_dmg),
                        getString(R.string.stab_range)
                    )
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "throwableweapon" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.explode_time),
                        getString(R.string.effect_range),
                        getString(R.string.fragments_count),
                        getString((R.string.damage))
                    )
                    for (i in buffer.indices) {
                        if (stringArray[i] == "smoke" || stringArray[i] == "stun") {
                            continue
                        }
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "container" -> {
                    buffer = arrayListOf()
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "secure" -> {
                    buffer = arrayListOf()
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "map" -> {
                    buffer = arrayListOf(
                        getString(R.string.name),
                        getString(R.string.playtime),
                        getString(R.string.players),
                        getString(R.string.enermy)
                    )
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${stringArray[i]}<br/>"
                    }
                }
                "helmet" -> {
                    buffer = arrayListOf(
                        "이름",
                        "방호력",
                        "방호 부위",
                        "내구도",
                        "도탄률",
                        "부작용",
                        "재질",
                        "청력 감소",
                        "헤드셋 착용 가능 여부",
                        "추가 사항"
                    )
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "armor_vest" -> {
                    buffer = arrayListOf("이름", "방호력", "방호 부위", "내구도", "실제 내구도", "부작용", "재질", "무게")
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "headset" -> {
                    buffer = arrayListOf("이름", "청명도", "저음역 컷 기준", "환경음 볼륨")
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
                "helmet_attachment" -> {
                    buffer = arrayListOf("이름", "방호력", "방호 부위", "내구도", "도탄률", "부작용", "재질", "소리 감소율")
                    for (i in buffer.indices) {
                        text += "${buffer[i]} : ${
                            stringArray[i]
                                .replace(" || ", "<br/>")
                                .replace("\n", "<br/>")
                                .replace("[Pain]", "  <img src=\"[Pain]\" alt='Pain'>")
                                .replace("[Fracture]", "  <img src=\"[Fracture]\" alt='Fracture'>")
                                .replace(
                                    "[Contusion]",
                                    "  <img src=\"[Contusion]\" alt='Contusion'>"
                                )
                                .replace(
                                    "[onPainkiller]",
                                    "  <img src=\"[onPainkiller]\" alt='onPainkiller'>"
                                )
                                .replace(
                                    "[Fresh_Wound]",
                                    "  <img src=\"[Fresh_Wound]\" alt='Fresh Wound'>"
                                )
                                .replace(
                                    "[Bloodloss]",
                                    "  <img src=\"[Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace(
                                    "[Heavy_Bloodloss]",
                                    "  <img src=\"[Heavy_Bloodloss]\" alt='Bloodloss'>"
                                )
                                .replace("[Stun]", "  <img src=\"[Stun]\" alt='Stun'>")
                                .replace("{R", "<font color='red'>")
                                .replace("{B", "<font color='blue'>")
                                .replace("}", "</font>")
                        }<br/>"
                    }
                }
            }
            val html = Html.fromHtml(text, 0, ImageGetter(), null)
            tv_item_explain.text = html
            if (mods.size > 0) {
                val patterns = arrayListOf(
                    Pattern.compile("${getString(R.string.min_recoil)} 바로가기"),
                    Pattern.compile("${getString(R.string.max_ergo)} 바로가기"),
                    Pattern.compile("${getString(R.string.budget_modding)} 바로가기")
                )
                val transform = Linkify.TransformFilter { _, _ ->
                    ""
                }
                for (i in mods.indices) {
                    Linkify.addLinks(
                        tv_item_explain,
                        patterns[i],
                        "https://tarkov-gunsmith.com/loadout/${mods[i]}",
                        null,
                        transform
                    )
                }
            }


            supportActionBar?.title = stringArray[0]
            val imageResId = intent.getIntExtra("image", 0)
            loadWithWebp(this, iv_item_explain, imageResId)
            iv_item_explain.setOnClickListener {
                startActivity<PhotoActivity>(
                    "id" to imageResId
                )
            }

        }catch (e: Exception){
            Log.e("IEA", "${e.message}")
            toast("오류가 발생했습니다. ${e.message}")
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
   inner class ImageGetter: Html.ImageGetter {
        override fun getDrawable(source: String?): Drawable {
            var resID: Int? = null
            if(source == "[Pain]"){
                resID = R.drawable.pain
            }
            if(source == "[Fracture]"){
                resID = R.drawable.fracture
            }
            if(source == "[Contusion]"){
                resID = R.drawable.contusion
            }
            if(source == "[onPainkiller]"){
                resID = R.drawable.on_painkillers
            }
            if(source == "[Fresh_Wound]"){
                resID = R.drawable.fresh_wound
            }
            if(source == "[Bloodloss]"){
                resID = R.drawable.bleeding
            }
            if(source == "[Heavy_Bloodloss]"){
                resID = R.drawable.heavy_bleeding
            }
            if(source == "[Restore_HP]"){
                resID = R.drawable.restores_hp
            }
            if(source == "[Stun]"){
                resID = R.drawable.stun
            }
            if(source == "[Toxin]"){
                resID = R.drawable.toxin
            }
            Log.d("IEA", "$resID")
            val drawable = resID?.let { this@ItemExplainActivity.resources.getDrawable(it, null) }
            drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            return drawable!!
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}