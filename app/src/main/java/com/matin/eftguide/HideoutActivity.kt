package com.matin.eftguide

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HideoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hideout)
        when(intent.getStringExtra("place")) {
            "화장실" -> {

            }
            "치료소" -> {

            }
            "발전기" -> {

            }
            "작업대" -> {

            }
            "조리대" -> {

            }
            "창고" -> {

            }
            "환기구" -> {

            }
            "조명" -> {

            }
            "물 수집기" -> {

            }
            "보안 시설" -> {

            }
            "사격장" -> {

            }
            "정보 시설" -> {

            }
            "난방 시설" -> {

            }
            "비트코인 채굴장" -> {

            }
            "술 제작소" -> {

            }
            "공기 청정 시설" -> {

            }
            "스캐브 교환소" -> {

            }
            "태양광 발전기" -> {

            }
            "도서관" -> {

            }
            "크리스마스 트리" -> {

            }
            
        }
    }

    
    fun switchFragment(fragment: Fragment, data: String = ""){
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_hideoutFrame, fragment.apply {
                arguments = Bundle().apply {
                    putString("where", data)
                }
        }).commit()
    }
}