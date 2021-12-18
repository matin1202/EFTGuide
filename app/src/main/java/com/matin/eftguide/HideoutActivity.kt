package com.matin.eftguide

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.util.Log
import com.matin.eftguide.fragment.hideout_related.HideoutAlmostFragment
import com.matin.eftguide.fragment.hideout_related.HideoutBitcoinFarmFragment
import kotlinx.android.synthetic.main.activity_hideout.*
import kotlinx.android.synthetic.main.fragment_hideout_almost.*

class HideoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hideout)
        Log.d("HA", intent.getStringExtra("place")!!)
        tb_hideout_text.text = intent.getStringExtra("place")
        setSupportActionBar(tb_hideout_almost)
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        when(intent.getStringExtra("place")) {
            "화장실" -> {
                switchFragment(HideoutAlmostFragment(), "lavatory")
            }
            "치료소" -> {
                switchFragment(HideoutAlmostFragment(), "medstation")
            }
            "발전기" -> {
                switchFragment(HideoutAlmostFragment(), "generator")
            }
            "작업대" -> {
                switchFragment(HideoutAlmostFragment(), "workbench")
            }
            "조리대" -> {
                switchFragment(HideoutAlmostFragment(), "nutrition_unit")
            }
            "창고" -> {
                switchFragment(HideoutAlmostFragment(), "stash")
            }
            "환기구" -> {
                switchFragment(HideoutAlmostFragment(), "vents")
            }
            "조명" -> {
                switchFragment(HideoutAlmostFragment(), "illumination")
            }
            "물 수집기" -> {
                switchFragment(HideoutAlmostFragment(), "water_collector")
            }
            "보안 시설" -> {
                switchFragment(HideoutAlmostFragment(), "security")
            }
            "사격장" -> {
                switchFragment(HideoutAlmostFragment(), "shooting_range")
            }
            "정보 시설" -> {
                switchFragment(HideoutAlmostFragment(), "intelligence_center")
            }
            "난방 시설" -> {
                switchFragment(HideoutAlmostFragment(), "heating")
            }
            "비트코인 채굴장" -> {
                switchFragment(HideoutBitcoinFarmFragment(), "bitcoin")
            }
            "술 제작소" -> {
                switchFragment(HideoutAlmostFragment(), "booze_generator")
            }
            "공기 청정 시설" -> {
                switchFragment(HideoutAlmostFragment(), "air_filtering_unit")
            }
            "스캐브 교환소" -> {
                switchFragment(HideoutAlmostFragment(), "scav_case")
            }
            "태양광 발전기" -> {
                switchFragment(HideoutAlmostFragment(), "solar_power")
            }
            "도서관" -> {
                switchFragment(HideoutAlmostFragment(), "library")
            }
            "크리스마스 트리" -> {
                switchFragment(HideoutAlmostFragment(), "christmas_tree")
            }
            "휴식 공간" -> {
                switchFragment(HideoutAlmostFragment(), "rest_space")
            }
        }
    }

    
    private fun switchFragment(fragment: Fragment, data: String = ""){
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_hideoutFrame, fragment.apply {
                arguments = Bundle().apply {
                    putString("where", data)
                }
        }).commit()
    }
}