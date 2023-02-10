package com.matin.eftguide

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.core.widget.TextViewCompat
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import com.matin.eftguide.data.Datas
import kotlinx.android.synthetic.main.activity_explain.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast

class ExplainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explain)

        try {

            if (prefs.getBoolean("adLoad", true)) {
                ea_adView.loadAd(AdLoaderClass().adRequest)
            }

            iv_ammo_image.imageResource = intent.getIntExtra("image", 0)
            val bullet = Datas.findBullet(intent.getStringExtra("bullet")!!)

            if (bullet.recoil.contains("+")){
                bullet.recoil = "<font color='blue'>${bullet.recoil}</font>"
            }
            if (bullet.recoil.contains("-")){
                bullet.recoil = "<font color='red'>${bullet.recoil}</font>"
            }
            if (bullet.acc.contains("+")){
                bullet.acc = "<font color='blue'>${bullet.acc}</font>"
            }
            if (bullet.acc.contains("-")){
                bullet.acc = "<font color='red'>${bullet.acc}</font>"
            }

            val stringArray = listOf(
                bullet.name,
                bullet.dmg,
                bullet.pene,
                bullet.a_dmg,
                bullet.acc,
                bullet.recoil,
                bullet.frag_chn,
                bullet.rico_chn,
                bullet.heavy,
                bullet.light,
                bullet.speed,
                bullet.special,
                bullet.sold_by,
                ""
            )
            val buffer = arrayListOf(
                tv_ammo_name,
                tv_ammo_damage,
                tv_ammo_penetration,
                tv_ammo_armor_dmg,
                tv_ammo_accuracy,
                tv_ammo_recoil,
                tv_ammo_fragmentation,
                tv_ammo_ricochet,
                tv_ammo_heavy_bleeding,
                tv_ammo_bleeding,
                tv_ammo_speed,
                tv_ammo_special,
                tv_ammo_sold_by,
                tv_ammo_table
            )

            for(i in buffer.indices){
                val html = Html.fromHtml(stringArray[i], 0, ImageGetter(), null)
                buffer[i].text = html
                if(i != 12){
                    TextViewCompat.setAutoSizeTextTypeWithDefaults(buffer[i], TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
                }
            }

            tv_ammo_caliber.text = bullet.caliber
            TextViewCompat.setAutoSizeTextTypeWithDefaults(tv_ammo_caliber, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

            /*val buffer2 = arrayListOf(
                "",
                "  <img src=\"damage\" alt='Damage'>",
                "  <img src=\"penetration\" alt='Penetration'>",
                "",
                "  <img src=\"accuracy\" alt='Accuracy'>",
                "  <img src=\"recoil\" alt='Recoil'>",
                "  <img src=\"fragmentation\" alt='Fragmentation'>",
                "  <img src=\"ricochet\" alt='Ricochet'>",
                "",
                "",
                "  <img src=\"speed\" alt='Speed'>",
                "",
                "  <img src=\"trading\" alt='Sold by'>",
                ""
            )
            var text = ""
            for (i in 0 until buffer.size) {
                text += "${buffer[i]}${buffer2[i]} : ${
                    stringArray[i].split("{R").joinToString("<font color='red'>").split("{B")
                        .joinToString("<font color='blue'>").split("}").joinToString("</font>")
                        .split("\n").joinToString("<br>")
                }<br>"
            }
            val html = Html.fromHtml(text, 0, ImageGetter(), null)
            Too Old to Use   */


        }catch (e: Exception){
            Log.e("EA", "${e.message}")
            toast("오류가 발생했습니다. ${e.message}")
            finish()
        }
    }

    inner class ImageGetter: Html.ImageGetter {
        @SuppressLint("UseCompatLoadingForDrawables")
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