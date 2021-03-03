package com.matin.eftguide

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import com.matin.eftguide.fragment.*
import com.matin.eftguide.fragment.ArmorFragment
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        if(prefs.getBoolean("adLoad", true)) {
            menu_adView.loadAd(AdLoaderClass().adRequest)
        }

        supportActionBar?.title = intent.getStringExtra("title")

        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.action_title))

        when(intent.getStringExtra("where")){
            "ammo" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, AmmoFragment())
                    .commit()
            }
            "weapon" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, WeaponFragment())
                    .commit()
            }
            /*"container" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, ContainerFragment())
                    .commit()
            }   */
            "item" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, ItemFragment())
                    .commit()
            }
            "heal" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, HealFragment())
                    .commit()
            }
            "provision" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, ProvisionFragment())
                    .commit()
            }
            "map" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, MapFragment())
                    .commit()
            }
            "headset" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, HeadsetFragment())
                    .commit()
            }
            "dealer" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, DealerFragment())
                    .commit()
            }
            "dealer_explain" -> {
                val dealer = intent.getStringExtra("dealer")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, DealerExplainFragment().apply {
                        arguments = Bundle().apply {
                            putString("dealer", dealer)
                        }
                    })
                    .commit()
            }
            "gear" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, ArmorFragment())
                    .commit()
            }
            "helmet" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, HelmetFragment())
                    .commit()
            }
            "armor vest" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, ArmorVestFragment())
                    .commit()
            }
            "helmet attachment" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, HelmetAttachmentFragment())
                    .commit()
            }
            "night vision" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, NightVisionFragment())
                    .commit()
            }
            "quest" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, QuestFragment())
                    .commit()
            }
            "quest list" -> {
                val dealer = intent.getStringExtra("dealer")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_menu, QuestListFragment().apply{
                        arguments = Bundle().apply {
                            putString("dealer", dealer)
                        }
                    })
                    .commit()
            }
            else -> {
                Toast.makeText(applicationContext, "준비 중입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}