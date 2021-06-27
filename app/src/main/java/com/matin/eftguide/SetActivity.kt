package com.matin.eftguide

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.google.gson.JsonObject
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_set.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import org.jetbrains.anko.browse
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class SetActivity : BaseActivity(), PurchasesUpdatedListener, OnUserEarnedRewardListener {
    private var _rewardLoad = false
    private val context: Context by lazy { this }
    private val site = "https://escapefromtarkov.fandom.com/wiki/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)

        sa_adView.loadAd(AdLoaderClass().adRequest)
        MobileAds.initialize(this)


        tv_setting.setOnClickListener {
            selectThemeDialog()
        }
        tv_adSetting.setOnClickListener{
            selectAdDialog()
        }
        tv_donation.setOnClickListener {
            loadRewardedAd()
        }
        tv_a_thousands.setOnClickListener {
            BillingClass(this, "donate1")
        }
        tv_five_thousands.setOnClickListener {
            BillingClass(this, "donate2")
        }
        tv_ten_thousands.setOnClickListener {
            BillingClass(this, "donate3")
        }
        million.onClick {
            BillingClass(this@SetActivity, "crazy")
        }
        remove_db.onClick {
            removeDB()
        }
        tv_open_chat.onClick {
            it?.snackbar("이동하시겠습니까?", "이동"){
                browse("https://open.kakao.com/me/matin")
            }
        }
        s_realtime_db.onCheckedChange { _, isChecked ->
            prefs.edit()
                .putBoolean("realtime", isChecked)
                .apply()
            toast("저장되었습니다.")
            if(isChecked){
                toast("불안정해질 수 있습니다.")
                downloadDB()
            }
        }
        s_realtime_db.visibility = View.GONE
    }

    private fun removeDB(){
        val files = filesDir.listFiles()
        if(!files.isNullOrEmpty()) {
            for (i in files.indices) {
                files[i].delete()
            }
        }
        toast("임시 파일을 삭제했습니다.")
    }



    private fun selectAdDialog(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.ad_dialog, null)
        val adOn = view.findViewById<RadioButton>(R.id.rb_ad_on)
        val adOff = view.findViewById<RadioButton>(R.id.rb_ad_off)
        when(prefs.getBoolean("adLoad", true)){
            true -> {
                adOn.isChecked = true
            }
            false -> {
                adOff.isChecked = true
            }
        }

        val dialog = AlertDialog.Builder(this, R.style.MyDialogTheme)
            .setTitle("광고 표시 여부")
            .setPositiveButton("저장"){ _, _ ->
                if(adOn.isChecked){
                    prefs.edit().putBoolean("adLoad", true).apply()
                }
                else{
                    prefs.edit().putBoolean("adLoad", false).apply()
                }
            }
            .setNegativeButton("취소", null)
            .create()
        dialog.setView(view)
        dialog.show()
    }

    private fun selectThemeDialog(){
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.theme_dialog, null)
        val darkMode = view.findViewById<RadioButton>(R.id.rb_dark)
        val lightMode = view.findViewById<RadioButton>(R.id.rb_light)
        val defaultMode = view.findViewById<RadioButton>(R.id.rb_default)
        when(prefs.getString("theme", "default")){
            "light" -> {
                lightMode.isChecked = true
            }
            "dark" -> {
                darkMode.isChecked = true
            }
            "default" -> {
                defaultMode.isChecked = true
            }
        }

        val dialog = AlertDialog.Builder(this, R.style.MyDialogTheme)
            .setTitle("테마")
            .setPositiveButton("저장"){ _, _ ->
                toast("테마 적용을 위해 앱을 재시작합니다.")
                if(darkMode.isChecked){
                    prefs.edit().putString("theme", "dark").apply()
                }
                if(lightMode.isChecked){
                    prefs.edit().putString("theme", "light").apply()
                }
                if(defaultMode.isChecked){
                    prefs.edit().putString("theme", "default").apply()
                }
                System.runFinalization()
                startActivity<MainActivity>()
                finishAffinity()
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.setView(view)
        dialog.show()
    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {

    }

    private fun loadRewardedAd(){
        toast("광고를 로딩 중입니다.")
        if(!_rewardLoad) {
            _rewardLoad = true
            RewardedInterstitialAd.load(
                this,
                "ca-app-pub-3429208671349104/2021930393",
                AdRequest.Builder().build(),
                object : RewardedInterstitialAdLoadCallback() {
                    override fun onAdLoaded(p0: RewardedInterstitialAd) {
                        _rewardLoad = false
                        toast("광고를 게시합니다.")
                        p0.show(this@SetActivity, this@SetActivity)
                    }

                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        toast("광고 게시에 실패하였습니다. ${p0.responseInfo}")
                    }

                }
            )
        }
        else{
            toast("광고가 이미 로딩 중입니다.")
        }
    }

    override fun onUserEarnedReward(p0: RewardItem) {
        toast("광고를 시청해주셔서 감사드립니다. :D")
    }

    private fun downloadDB(){
        //ammo
        val ammoRoutine = CoroutineScope(IO).launch {
            lateinit var allAmmoDoc: Document
            doAsync {
                    allAmmoDoc = Jsoup.connect(site + "Ballistics").get()
                }
            val allAmmoContents = allAmmoDoc.select(".wikitable_sortable_jquery-tablesorter").select("tbody").select("tr")
            var lists = ArrayList<Ammo>()
            for(i in allAmmoContents.indices){
                if(allAmmoContents[i].attr("id")!=""){
                    val contents = allAmmoContents[i].select("td")
                    Ammo(contents[1].text(), contents[2].text(), contents[3].text(), contents[4].text(), contents[5].text(), contents[6].text(), contents[7].text(), contents[8].text(), "", contents[9].text(), "", "", "", contents[10].text())
                }
                else{
                    
                }
            }
        }
    }

    data class Ammo(val name: String, val damage: String, val penetratePower: String, val armorDmg: String, val accuracy: String = "", val recoil: String = "", val fragChance: String, val ricochetChance: String, val lightBld: String = "", val heavyBld: String = "", val speed: String, val feature: String = "", val soldBy: String, val effectiveness: String)
}