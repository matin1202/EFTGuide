package com.matin.eftguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import com.matin.eftguide.fragment.map_related.MenuUsableKeysFragment
import com.matin.eftguide.fragment.map_related.MenuExtractionFragment
import com.matin.eftguide.fragment.map_related.MenuFeatureFragment
import com.matin.eftguide.fragment.map_related.MenuMapFragment
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "map")
            param(FirebaseAnalytics.Param.ITEM_NAME, intent.getStringExtra("maps")!!)
        }

        if(prefs.getBoolean("adLoad", true)) {
            map_adView.loadAd(AdLoaderClass().adRequest)
        }

        bn_map_navigation.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_mapFrame,
            MenuMapFragment().apply{
                arguments = Bundle().apply {
                    putString("map", intent.getStringExtra("maps"))
                    putInt("image", intent.getIntExtra("image", 0))
                }
            }
        ).commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        
        when(p0.itemId){
            R.id.item_menu_explain -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_mapFrame,
                    MenuUsableKeysFragment().apply{
                        arguments = Bundle().apply {
                            putString("map", intent.getStringExtra("maps"))
                            putInt("image", intent.getIntExtra("image", 0))
                        }
                    }
                ).commit()
            }
            R.id.item_menu_extraction -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_mapFrame,
                    MenuExtractionFragment().apply{
                        arguments = Bundle().apply {
                            putString("map", intent.getStringExtra("maps"))
                            putInt("image", intent.getIntExtra("image", 0))
                        }
                    }
                ).commit()
            }
            
            
            R.id.item_menu_features -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_mapFrame,
                    MenuFeatureFragment().apply{
                        arguments = Bundle().apply {
                            putString("map", intent.getStringExtra("maps"))
                            putInt("image", intent.getIntExtra("image", 0))
                        }
                    }
                ).commit()
            }
            R.id.item_menu_maps -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_mapFrame,
                    MenuMapFragment().apply{
                        arguments = Bundle().apply {
                            putString("map", intent.getStringExtra("maps"))
                            putInt("image", intent.getIntExtra("image", 0))
                        }
                    }
                ).commit()
            }
        }
        return true
    }
}