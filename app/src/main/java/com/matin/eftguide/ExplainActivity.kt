package com.matin.eftguide

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_explain.*

class ExplainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)

        if(prefs.getBoolean("adLoad", true)) {
            ea_adView.loadAd(AdLoaderClass().adRequest)
        }

        val ammo = intent.getStringExtra("type")!!.split(" ").joinToString("_").split("\"").joinToString("").split(".").joinToString("").split("-").joinToString("_").split("/").joinToString("")
        Log.d("EA", ammo)
        val resId = resources.getIdentifier(ammo, "array", packageName)
        val stringArray = resources.getStringArray(resId)
        val buffer = arrayListOf(getString(R.string.name), getString(R.string.damage), getString(R.string.penetration_power), getString(R.string.armor_damage), getString(R.string.accruracy), getString(R.string.recoil), getString(R.string.fragmentation_chance), getString(R.string.ricochet_chance), getString(R.string.speed), getString(R.string.special_effect), getString(R.string.sold_by), getString(R.string.penetrationTable))
        val buffer2 = arrayListOf("", "  <img src=\"damage\" alt='Damage'>", "  <img src=\"penetration\" alt='Penetration'>", "", "  <img src=\"accuracy\" alt='Accuracy'>", "  <img src=\"recoil\" alt='Recoil'>", "  <img src=\"fragmentation\" alt='Fragmentation'>", "  <img src=\"ricochet\" alt='Ricochet'>", "  <img src=\"speed\" alt='Speed'>", "", "  <img src=\"trading\" alt='Sold by'>", "")
        var text = ""
        for(i in 0 until buffer.size){
            text += "${buffer[i]}${buffer2[i]} : ${stringArray[i].split("{R").joinToString("<font color='red'>").split("{B").joinToString("<font color='blue'>").split("}").joinToString("</font>").split("\n").joinToString("<br>")}<br>"
        }
        val html = Html.fromHtml(text, 0, ImageGetter(), null)
        tv_explain_test?.text = html
    }

    inner class ImageGetter: Html.ImageGetter {
        override fun getDrawable(source: String?): Drawable {
            Log.d("IAE", "$source")
            val resID = resources.getIdentifier(source, "drawable", packageName)
            val drawable: Drawable? = resources.getDrawable(resID, null)
            Log.d("IAE", "$drawable $source")
            drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            return drawable!!
        }
    }                 
}