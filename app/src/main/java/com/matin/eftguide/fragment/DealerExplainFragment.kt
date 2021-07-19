package com.matin.eftguide.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_dealer_explain.*
import kotlinx.coroutines.*
import org.jetbrains.anko.support.v4.toast
import org.jsoup.Jsoup
import java.util.*

class DealerExplainFragment : Fragment(), View.OnClickListener {

    private lateinit var dealer: String
    private val job: Job = Job()
    private val uri = "https://escapefromtarkov.gamepedia.com/File:"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dealer = arguments?.getString("dealer")!!
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dealer_explain, container, false)
        try {
            view.findViewById<TextView>(R.id.tv_dealer_name).text = dealer

            val dealers = mutableListOf<String>(
                "Prapor",
                "Therapist",
                "Fence",
                "Skier",
                "Peacekeeper",
                "Mechanic",
                "Ragman",
                "Jaeger"
            )
            val resID = mutableListOf<Int>(
                R.drawable.prapor,
                R.drawable.therapist,
                R.drawable.fence,
                R.drawable.skier,
                R.drawable.peacekeeper,
                R.drawable.mechanic,
                R.drawable.ragman,
                R.drawable.jaeger
            )
            val requirements = mutableListOf<String>(
                "15레벨, 0.20, 750,000루블||22레벨, 0.35, 1,500,000루블||33레벨, 0.50, 2,300,000루블",
                "13레벨, 0.15, 400,000루블||20레벨, 0.30, 700,000루블||32레벨, 0.60, 900,000루블",
                "1레벨, 6.00 0루블||유저 스캐브 플레이시|| 다른 스캐브 사살 -0.02|||| 보스 사살 -0.22||PMC 사살 +0.1||배신한 스캐브(스캐브 쏜 유캐브) 사살 +0.1||보스나 스캐브가 적 죽이는 거 도울 시 오름||택시 탈출구로 탈출시 +0.25(런쓰루시 +0.125)",
                "15레벨, 0.20, 800,000루블||28레벨, 0.48, 1,600,000루블||35레벨, 0.75, 2,600,000루블",
                "10레벨, 0.00, 11,000달러||18레벨, 0.30, 25,000달러||29레벨, 0.60, 32,000달러",
                "20레벨, 0.15, 750,000루블||30레벨, 0.30, 1,600,000루블||40레벨, 0.60, 2,500,000루블",
                "15레벨, 0.00, 750,000루블||30레벨, 0.30, 1,600,000루블||40레벨, 0.60, 2,500,000루블",
                "15레벨, 0.20, 750,000루블||22레벨, 0.35, 1,500,000루블||33레벨, 0.50, 2,300,000루블"
            )

            if (dealer == "Fence") {
                val requirement = view.findViewById<TextView>(R.id.tv_dealer_requirement)
                view.findViewById<ImageView>(R.id.iv_dealer_profile)
                    .setImageResource(R.drawable.fence)
                requirement.text = requirements[2].replace("||", "\n")
                requirement.textSize = 11.0f
                view.findViewById<TextView>(R.id.tv_dealer_explain).setOnClickListener(this)
                view.findViewById<TextView>(R.id.tv_dealer_level_i).visibility = View.GONE
                view.findViewById<TextView>(R.id.tv_dealer_level_ii).visibility = View.GONE
                view.findViewById<TextView>(R.id.tv_dealer_level_iii).visibility = View.GONE
                view.findViewById<TextView>(R.id.tv_dealer_level_iv).visibility = View.GONE
                return view
            }

            for (i in dealers.indices) {
                if (dealer == dealers[i]) {
                    view.findViewById<ImageView>(R.id.iv_dealer_profile).setImageResource(resID[i])
                    val cache = requirements[i].split("||")
                    var requirement = ""
                    for (y in cache.indices) {

                        requirement += "상인 ${y + 2}레벨 요구 조건\n - ${cache[y].split(", ")[0]}, 우호도 ${
                            cache[y].split(
                                ", "
                            )[1]
                        }이상\n - 소비량 ${cache[y].split(", ")[2]} 이상\n"
                    }
                    view.findViewById<TextView>(R.id.tv_dealer_requirement).text = requirement
                }
            }

            view.findViewById<TextView>(R.id.tv_dealer_explain).setOnClickListener(this)
            view.findViewById<TextView>(R.id.tv_dealer_level_i).setOnClickListener(this)
            view.findViewById<TextView>(R.id.tv_dealer_level_ii).setOnClickListener(this)
            view.findViewById<TextView>(R.id.tv_dealer_level_iii).setOnClickListener(this)
            view.findViewById<TextView>(R.id.tv_dealer_level_iv).setOnClickListener(this)

        }catch(e: Exception) {
            toast("알수 없는 에러가 발생했습니다.")
        }
            return view
        }

        override fun onClick(v: View?) {
            try {
                val cm =
                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                val isConnected: Boolean =
                    (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
                Log.d("DEF", "onClick: Is Internet connected? $isConnected}")
                if (!isConnected) {
                    toast("인터넷에 연결해 주세요...")
                    return
                }
                when (v?.id) {
                    R.id.tv_dealer_level_i -> {
                        getImage(1)
                    }
                    R.id.tv_dealer_level_ii -> {
                        getImage(2)
                    }
                    R.id.tv_dealer_level_iii -> {
                        getImage(3)
                    }
                    R.id.tv_dealer_level_iv -> {
                        getImage(4)
                    }
                    R.id.tv_dealer_explain -> {
                        val resID = resources.getIdentifier(
                            "${dealer.toLowerCase(Locale.ROOT)}_explain",
                            "string",
                            context?.packageName
                        )
                        Log.d("DEF", "onClick: $resID : ${dealer.toLowerCase(Locale.ROOT)}_explain")
                        AlertDialog.Builder(context, R.style.MyDialogTheme)
                            .setTitle(dealer)
                            .setMessage(getString(resID))
                            .setPositiveButton("닫기") { _, _ -> }
                            .show()
                    }
                }
            }catch(e: Exception) {
                 toast("알 수 없는 에러가 발생했습니다.")
            }
        }

        private fun getImage(lv: Int) {
            try {
                toast("이미지를 불러옵니다...")
                val scope = CoroutineScope(Dispatchers.Main + job)
                scope.launch {
                    val crawling = async(Dispatchers.IO) {
                        try {
                            val doc =
                                Jsoup.connect("$uri$dealer${lv}Stock.png").timeout(20000).get()
                            val target = doc.select("#file").select("a").attr("href")
                            Log.d("DEF", target)
                            return@async target
                        } catch (e: Exception) {
                            return@async null
                        }
                    }


                    val builder = AlertDialog.Builder(context, R.style.MyDialogTheme)
                    val dialogView = layoutInflater.inflate(R.layout.image_dialog, null)

                    val url: String? = crawling.await()

                    if (url.isNullOrEmpty()) {
                        toast("이미지를 불러오는 데 실패하였습니다.")
                        return@launch
                    }

                    val dialogImage = dialogView.findViewById<ImageView>(R.id.iv_dialog)
                    if (context != null) {
                        Glide.with(context!!)
                            .load(url)
                            .placeholder(R.drawable.loadingimage)
                            .fallback(R.drawable.loadingimage)
                            .thumbnail(0.5f)
                            .into(dialogImage)
                        dialogImage.setOnClickListener {
                            startActivity(
                                (Intent(context, PhotoActivity::class.java).putExtra("url", url)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                            )
                        }
                        builder.setView(dialogView)
                            .setPositiveButton("닫기") { _, _ ->

                            }
                            .setTitle("$dealer 우호도 $lv 레벨 상품 목록")
                            .show()
                    }
                }
            }catch (e: Exception){
                toast("알 수 없는 에러가 발생했습니다.")
            }
        }
    }