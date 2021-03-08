package com.matin.eftguide.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.QuestActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_headset.view.*
import kotlinx.android.synthetic.main.image_dialog.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*
import kotlin.collections.ArrayList

class QuestListFragment : Fragment() {

    private val dealer: String by lazy{  arguments?.getString("dealer")!!  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headset, container, false)

        val list = ArrayList<QLRecycler>()

        val prapor = arrayListOf("Debut(데뷔)", "Search Mission(수색 임무)", "Checking(찾기)", "Shootout Picnic(사격 연습)", "Delivery from the past(과거로부터의 배달)", "BP depot(유류창고 확보)", "The Bunker - Part 1(벙커 1)", "The Bunker - Part 2(벙커 2)", "Bad rep evidence(나쁜 평판의 증거)", "Ice cream cones(아이스크림 콘)", "Postman_Pat - Part 1(전달책 쓰다듬기 1)", "Shaking up teller(현금가방 흔들기)", "The Punisher - Part 1(처벌자 1)", "The Punisher - Part 2(처벌자 2)", "The Punisher - Part 3(처벌자 3)", "The Punisher - Part 4(처벌자 4)", "The Punisher - Part 5(처벌자 5)", "The Punisher - Part 6(처벌자 6)", "Anesthesia(마취)", "Grenadier(척탄병)", "Test drive - Part 1(시험 운행 1)", "Insomnia(불면증)", "Perfect Mediator(완벽한 중재자)", "Polikhim hobo(Polikhim의 노동자)", "Regulated Materials(규제 물질)", "Big Customer(거물)", "No Offence(나쁜 뜻은 아니었어)")
        val therapist = arrayListOf("Shortage(부족)", "Operation Aquarius - Part 1(작전명 물병자리 1)", "Operation Aquarius - Part 2(작전명 물병자리 2)", "Sanitary Standards - Part 1(위생 기준 1)", "Sanitary Standards - Part 2(위생 기준 2)", "Painkiller(진통제)", "Pharmacist(약사)", "Car repair(차 고치기)", "Hippocratic Vow(히포크라테스 선서)", "Supply plans(공급 계획)", "Health Care Privacy - Part 1(개인 의료 정보 보호 1)", "Health Care Privacy - Part 2(개인 의료 정보 2)", "Health Care Privacy - Part 3(개인 의료 정보 3)", "Health Care Privacy - Part 4(개인 의료 정보 4)", "Health Care Privacy - Part 5(개인 의료 정보 5)", "An apple a day - keeps the doctor away(하루에 사과 하나면 의사를 멀리한다.)", "Athlete(운동선수)", "Private clinic(개인 의료소)", "Decontamination service(제염 작업)", "General wares(일반적인 물건)", "Colleagues - Part 1(동료 1)", "Colleagues - Part 2(동료 2)", "Colleagues - Part 3(동료 3)", "Postman Pat - Part 2(전달책 쓰다듬기)", "Out of curiosity(호기심에서)", "Trust again(신뢰 되찾기)")
        val skier = arrayListOf("Supplier(공급자)", "The Extortionist(탈취자)", "Stirrup(휘저어 놓다)", "What\'s on the flash drive?(USB에 뭐가 들어 있을까?)", "Golden Swag(훔친 금 장물)", "Lend lease - Part 1(무기 대여 1)", "Friend from the West - Part 1(서쪽에서 온 친구 1)", "Friend from the West - Part 2(서쪽에서 온 친구 2)", "Informed Means Armed(아는 것이 힘이다)", "Chumming(밑밥뿌리기)", "Silent Caliber(조용한 구경)", "Flint(냉혈한)", "Bullshit(개소리)", "Setup(준비)", "Chemical - Part 1(화학 물질 1)", "Chemical - Part 2(화학 물질 2)", "Chemical - Part 3(화학 물질 3)", "Chemical - Part 4(화학 물질 4)", """"Vitamins" - Part 1(비타민 1)""", "\"Vitamins\" - Part 2(비타민 2)", "Loyalty Buyout(충성심 매수)", "Kind of Sabotage(방해 공작의 일종)", "Rigged Game(조작된 게임)")

        when(dealer){
            "prapor" -> {
                for(i in prapor.indices){
                    list.add(QLRecycler(context, prapor[i]))
                }
            }
            "therapist" -> {
                for(i in therapist.indices){
                    list.add(QLRecycler(context, therapist[i]))
                }
            }
            "skier" -> {
                for(i in skier.indices){
                    list.add(QLRecycler(context, skier[i]))
                }
            }
        }

        val adapter = QLAdapter(list)
        val recyclerView = view.rl_headset_menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }

    class QLRecycler(val context: Context?, val title: String)

    class QLAdapter(private val items: ArrayList<QLRecycler>):
        RecyclerView.Adapter<QLAdapter.ViewHolder>() {
        private var previousPosition = -1

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val view = v
            fun bind(item: QLRecycler){
                view.main_textView.text = item.title
                view.main_imageView.imageAlpha = Color.TRANSPARENT
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    view.main_textView.setAutoSizeTextTypeUniformWithConfiguration(
                        8,
                        24,
                        2,
                        TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                    )
                }
                else{
                    view.main_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0F)
                }

                view.onClick{
                    item.context?.startActivity(Intent(item.context, QuestActivity::class.java).putExtra("quest", "q_${item.title.split("(")[0].replace("\"", "").replace("\'", "").replace("?", "").replace(" - ", "_").replace(" ", "_").toLowerCase(Locale.ROOT)}"))
                    return@onClick
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
            return ViewHolder(inflatedView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(position > previousPosition ){
                val ani = AlphaAnimation(0.0f, 2.0f)
                ani.fillAfter = true
                ani.duration = 1000
                holder.itemView.animation = ani
            }

            previousPosition = position
            val item = items[position]
            holder.apply{
                bind(item)
                itemView.tag = item
            }
        }
    }
}