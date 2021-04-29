package com.matin.eftguide.base

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.matin.eftguide.R
import org.jetbrains.anko.image


open class BaseActivity : AppCompatActivity() {
    lateinit var prefs: SharedPreferences

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = applicationContext.getSharedPreferences("apps", MODE_PRIVATE)
        when(prefs.getString("theme", "default")){
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "default" -> {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }

        MobileAds.initialize(this)

    }


}

fun loadWithWebp(ctx: Context, imageView: ImageView, resId: Int) {
    Glide.with(ctx)
        .asDrawable()
        .load(resId)
        .into(object: SimpleTarget<Drawable>(){
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                imageView.image = resource
                if(resource is Animatable){
                    (resource as Animatable).start()
                }
            }
        })
}