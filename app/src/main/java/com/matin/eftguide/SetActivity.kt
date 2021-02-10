package com.matin.eftguide

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_set.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class SetActivity : BaseActivity(), PurchasesUpdatedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)
        val billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build()

        sa_adView.loadAd(AdLoaderClass().adRequest)

        tv_setting.setOnClickListener {
            selectThemeDialog()
        }
        tv_adSetting.setOnClickListener{
            selectAdDialog()
        }
        tv_donation.setOnClickListener {
            toast("준비중입니다.")
        }
        tv_ten_thousands.setOnClickListener {
            BillingClass(this, "test")
        }
        million.onClick {
            BillingClass(this@SetActivity, "crazy")
        }
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
                if(darkMode.isChecked){
                    prefs.edit().putString("theme", "dark").apply()
                }
                if(lightMode.isChecked){
                    prefs.edit().putString("theme", "light").apply()
                }
                if(defaultMode.isChecked){
                    prefs.edit().putString("theme", "default").apply()
                }
            }
            .setNegativeButton("취소", null)
            .create()

        dialog.setView(view)
        dialog.show()
    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {

    }
}