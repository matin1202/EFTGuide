package com.matin.eftguide

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_rig.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class RigActivity : BaseActivity(), View.OnClickListener {
    private lateinit var data: Array<String>
    private var isInside = false
    private val mFirebaseAnalytics by lazy{ FirebaseAnalytics.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rig)

        data = resources.getStringArray(resources.getIdentifier(intent.getStringExtra("type")!!.replace(" ", "_").replace("\"", "")
            .replace("\'", "").replace(".", "").replace("-", "_").replace("/", "")
            .replace("(", "").replace(")", "").replace("||", "").toLowerCase(Locale.ROOT), "array", packageName))

        val mBundle = Bundle()
        mBundle.putString(FirebaseAnalytics.Param.ITEM_NAME, intent.getStringExtra("type")!!.replace(" ", "_").replace("\"", "")
            .replace("\'", "").replace(".", "").replace("-", "_").replace("/", "")
            .replace("(", "").replace(")", "").replace("||", "").toLowerCase(Locale.ROOT))
        mBundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "IEA")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, mBundle)

        if(prefs.getBoolean("adLoad", true)) {
            rig_adView.loadAd(AdLoaderClass().adRequest)
        }

        val title = mutableListOf("이름", "방호 등급", "방호 부위", "표기 내구도", "실질 내구도", "패널티", "재질", "칸 수 대비 용량", "무게")
        var text = ""
        for(i in title.indices){
            if(data[i] == "0"){
                continue
            }
            val temp = title[i] + " : " + data[i].replace("{R", "<font color='red'>").replace("{B", "<font color='blue'>")                                                       .replace("}", "</font>") + "<br/>"
            text += temp
        }
        val htmlText = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        tv_rig_explain.text = htmlText
        iv_rig_rig.imageResource = intent.getIntExtra("image", R.drawable.abg)
        bt_rig_switch.setOnClickListener(this)
        iv_rig_rig.onClick {
            val image = if(!isInside){intent.getIntExtra("image", R.drawable.abg)}else{resources.getIdentifier(data[9], "drawable", packageName)}
            startActivity<PhotoActivity>("id" to image)
        }
        supportActionBar?.title = data[0]
    }

    override fun onClick(view: View?) {
        when(view){
            iv_rig_rig -> {
                
            }
            bt_rig_switch -> {
                if(!isInside){
                    bt_rig_switch.text = "모습 보기"
                    Log.d("RA", data[9])
                    val resId = resources.getIdentifier(data[9], "drawable", packageName)
                    isInside = true
                    iv_rig_rig.imageResource = resId
                }
                else{
                    bt_rig_switch.text = "내부 보기"
                    iv_rig_rig.imageResource = intent.getIntExtra("image", R.drawable.abg)
                    isInside = false
                }
            }
        }
    }
}