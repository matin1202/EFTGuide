package com.matin.eftguide.classes

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.activity_test_and_debug.*

public class BackPressCloserHandler {
    private var backKeyClick: Long = 0
    private lateinit var activity: Activity

    fun onBackPress(act: Activity){
        this.activity = act
        if(System.currentTimeMillis() - backKeyClick >= 2000){
            backKeyClick = System.currentTimeMillis()
            showAdDialog()
            return
        }
        if(System.currentTimeMillis() - backKeyClick < 2000){
            activity.finish()                         
        }
    }

    private fun showAdDialog(){
        val inflater = activity.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.ad_diaglog, null)
        val adRequest = AdRequest.Builder().build()
        view.findViewById<AdView>(R.id.av_dialog).loadAd(adRequest)

        val alertDialog = AlertDialog.Builder(activity, R.style.MyDialogTheme)
            .setTitle("닫기 버튼을 눌러 종료합니다.")
            .setPositiveButton("닫기"){ _, _ ->
                activity.finish()
            }
            .setNegativeButton("취소", null)
            .create()

        alertDialog.setView(view)
        alertDialog.show()
    }
}