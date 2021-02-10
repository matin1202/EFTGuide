package com.matin.eftguide

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.matin.eftguide.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar

class ExtraActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_extra)
        ExtraUI().setContentView(this)
    }
}

class ExtraUI : AnkoComponent<ExtraActivity> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<ExtraActivity>): View = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            imageView(R.mipmap.ic_launcher){
                setOnClickListener {
                    it.longSnackbar("유튜브 채널로 이동하시겠습니까?", "이동"){
                        browse("https://www.youtube.com/channel/UCtdmi566_g4EGG40XBmhOsQ?view_as=subscriber'>ROKABLYAK's youtube=", true)
                    }
                }
            }.lparams{
                width = wrapContent
                height = wrapContent
                padding = dip(16)
                margin = dip(16)
                gravity = Gravity.CENTER
            }

            textView {
                text = context.getString(R.string.special_thanks)
                textSize = 24f
            }.lparams(width = wrapContent, height = wrapContent)

            textView {
                text = "본 어플에는 Naver의 나눔 스퀘어 라운드 폰트가 적용되어 있습니다."
                setOnClickListener {
                    browse("https://hangeul.naver.com/2017/nanum", true)
                }
            }.lparams(width = wrapContent, height = wrapContent)

        }
    }
}