package com.matin.eftguide

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_quest.*
import com.matin.eftguide.fragment.quest_related.*

class QuestActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        if(prefs.getBoolean("adLoad", true)) {
            quest_adView.loadAd(AdLoaderClass().adRequest)
        }

        bn_quest_navigation.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_questFrame,
            MenuQuestExplainFragment().apply{
                arguments = Bundle().apply {
                    putString("quest", intent.getStringExtra("quest"))
                    putInt("image", intent.getIntExtra("image", 0))
                }
            }
        ).commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        
        when(p0.itemId){
            R.id.item_menu_dialogue -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_questFrame,
                    MenuDialogueFragment().apply{
                        arguments = Bundle().apply {
                            putString("quest", intent.getStringExtra("quest"))
                        }
                    }
                ).commit()
            }
            R.id.item_menu_quest -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_questFrame,
                    MenuQuestExplainFragment().apply{
                        arguments = Bundle().apply {
                            putString("quest", intent.getStringExtra("quest"))
                        }
                    }
                ).commit()
            }
            R.id.item_menu_quest_maps -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fl_questFrame,
                    MenuQuestMapFragment().apply{
                        arguments = Bundle().apply {
                            putString("quest", intent.getStringExtra("quest"))
                        }
                    }
                ).commit()
            }
        }
        return true
    }
}