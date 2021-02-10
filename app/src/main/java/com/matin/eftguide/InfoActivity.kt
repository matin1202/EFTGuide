package com.matin.eftguide

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.matin.eftguide.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick

class InfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InfoUI().setContentView(this)
    }
}

class InfoUI : AnkoComponent<InfoActivity>{

    private var times = 10
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<InfoActivity>): View = with(ui) {
        verticalLayout {
            textView {
                this.text = "개발자 정보\nmatin1202\n\n${context.getString(R.string.copyright)}"
                this.textColor = R.color.text_color
                this.textSize = 24.0f
                this.gravity = Gravity.CENTER
                onClick {
                    if(times!=0){
                        times--
                    }
                    else{
                        times = 10
                        startActivity<TestAndDebugActivity>()
                    }
                }
            }.lparams {
                margin = dip(16)
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }
            textView {
                this.text = "정보 및 사진 출처\nhttps://escapefromtarkov.gamepedia.com/Escape_from_Tarkov_Wiki"
                this.textColor = R.color.text_color
                this.textSize = 24.0f
                this.gravity = Gravity.CENTER
                onClick {
                    it?.snackbar("해당 사이트로 이동하시겠습니까?", "이동"){
                        browse("https://escapefromtarkov.gamepedia.com/Escape_from_Tarkov_Wiki", true)
                    }
                }
            }.lparams{
                margin = dip(16)
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }
            textView {
                this.text = "몇몇 맵들의 출처 \n https://gall.dcinside.com/mgallery/board/view/?id=eft&no=647996 (MOBILIS)"
                this.textColor = R.color.text_color
                this.textSize = 24.0f
                this.gravity = Gravity.CENTER
                onClick {
                    it?.snackbar("해당 사이트로 이동하시겠습니까?", "이동"){
                        browse("https://gall.dcinside.com/mgallery/board/view/?id=eft&no=647996", true)
                    }
                }
            }.lparams{
                margin = dip(16)
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }
            imageView{
                image = ContextCompat.getDrawable(context, R.drawable.cc_by_nc_sa)
            }.lparams{
                margin = dip(16)
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
            }
            textView {
                this.text = "아이콘 대부분의 출처\nhttps://icons8.kr/app"
                this.textColor = R.color.text_color
                this.textSize = 24.0f
                this.gravity = Gravity.CENTER
                onClick {
                    it?.snackbar("해당 사이트로 이동하시겠습니까?", "이동"){
                        browse("https://icons8.kr/app", true)
                    }
                }
            }.lparams{
                margin = dip(16)
                height = wrapContent
                width = wrapContent
                gravity = Gravity.CENTER
                backgroundColor = R.color.background
            }
        }
    }
}
