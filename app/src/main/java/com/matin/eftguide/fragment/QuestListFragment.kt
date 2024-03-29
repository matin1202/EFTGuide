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
import android.widget.Toast
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

    private val dealer: String by lazy { arguments?.getString("dealer")!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headset, container, false)

        val list = ArrayList<QLRecycler>()

        val prapor = arrayListOf(
            "Debut(데뷔)",
            "Search Mission(수색 임무)",
            "Checking(찾기)",
            "Shootout Picnic(사격 연습)",
            "Delivery from the past(과거로부터의 배달)",
            "BP depot(유류창고 확보)",
            "The Bunker - Part 1(벙커 1)",
            "The Bunker - Part 2(벙커 2)",
            "Bad rep evidence(나쁜 평판의 증거)",
            "Ice cream cones(아이스크림 콘)",
            "No place for renegades(변절자를 위한 곳은 없다)",
            "Documents(문서)",
            "Postman Pat - Part 1(전달책 쓰다듬기 1)",
            "Shaking up teller(현금가방 흔들기)",
            "The Punisher - Part 1(처벌자 1)",
            "The Punisher - Part 2(처벌자 2)",
            "The Punisher - Part 3(처벌자 3)",
            "The Punisher - Part 4(처벌자 4)",
            "The Punisher - Part 5(처벌자 5)",
            "The Punisher - Part 6(처벌자 6)",
            "Anesthesia(마취)",
            "Grenadier(척탄병)",
            "Test drive - Part 1(시험 운행 1)",
            "Insomnia(불면증)",
            "Perfect Mediator(완벽한 중재자)",
            "Polikhim hobo(폴리킴의 노동자)",
            "Regulated Materials(규제 물질)",
            "Big Customer(거물)",
            "No Offence(나쁜 뜻은 아니었어)",
            "Capturing Outposts(전초기지 점령)",
            "Intimidator(협박꾼)",
            "Escort(에스코트)",
            "The Bosses gathering(보스 모임)"
        )
        val therapist = arrayListOf(
            "Shortage(부족)",
            "Operation Aquarius - Part 1(작전명 물병자리 1)",
            "Operation Aquarius - Part 2(작전명 물병자리 2)",
            "Sanitary Standards - Part 1(위생 기준 1)",
            "Sanitary Standards - Part 2(위생 기준 2)",
            "Painkiller(진통제)",
            "Pharmacist(약사)",
            "Disease History(질병사)",
            "Car repair(차 고치기)",
            "Hippocratic Oath(히포크라테스 선서)",
            "Supply plans(공급 계획)",
            "Health Care Privacy - Part 1(개인 의료 정보 보호 1)",
            "Health Care Privacy - Part 2(개인 의료 정보 보호 2)",
            "Health Care Privacy - Part 3(개인 의료 정보 보호 3)",
            "Health Care Privacy - Part 4(개인 의료 정보 보호 4)",
            "Health Care Privacy - Part 5(개인 의료 정보 보호 5)",
            "An apple a day keeps the doctor away(하루에 사과 하나면 의사를 멀리한다.)",
            "Athlete(운동선수)",
            "Private clinic(개인 의료소)",
            "Decontamination service(제염 작업)",
            "General wares(일반적인 물건)",
            "Colleagues - Part 1(동료 1)",
            "Colleagues - Part 2(동료 2)",
            "Colleagues - Part 3(동료 3)",
            "Postman Pat - Part 2(전달책 쓰다듬기)",
            "Out of curiosity(호기심에서)",
            "Trust regain(신뢰 되찾기)",
            "Crisis(분쟁)",
            "Seaside Vacation(해변 휴가)"
        )
        val fence = arrayListOf("Collector(수집가)")
        val skier = arrayListOf(
            "Supplier(공급자)",
            "The Extortionist(탈취자)",
            "Stirrup(휘저어 놓다)",
            "What\'s on the flash drive?(USB에 뭐가 들어 있을까?)",
            "Golden Swag(훔친 금 장물)",
            "Lend lease - Part 1(무기 대여법 1)",
            "Friend from the West - Part 1(서쪽에서 온 친구 1)",
            "Friend from the West - Part 2(서쪽에서 온 친구 2)",
            "Informed Means Armed(아는 것이 힘이다)",
            "Chumming(밑밥뿌리기)",
            "Silent Caliber(조용한 구경)",
            "Flint(냉혈한)",
            "Bullshit(개소리)",
            "Setup(준비)",
            "Chemical - Part 1(화학 물질 1)",
            "Chemical - Part 2(화학 물질 2)",
            "Chemical - Part 3(화학 물질 3)",
            "Chemical - Part 4(화학 물질 4)",
            """"Vitamins" - Part 1(비타민 1)""",
            "\"Vitamins\" - Part 2(비타민 2)",
            "Loyalty Buyout(충성심 매수)",
            "Kind of Sabotage(방해 공작의 일종)",
            "Rigged Game(조작된 게임)",
            "Safe corridor(안전한 통로)",
            "Night Sweep(야간 청소)",
            "Missing Cargo(잃어버린 창고)"
        )
        val peacekeeper = arrayListOf(
            "Fishing Gear(낚시 용품)",
            "Experience Exchange(경험과 맞바꾸다)",
            "Revision - Part 1(개정 1)",
            "Revision - Part 2(개정 2)",
            "Tigr Safari(티그르 여행)",
            "Scrap Metal(고철 덩어리)",
            "Eagle Eye(예리한 관찰력)",
            "Humanitarian Supplies(인도적 보급품)",
            "Spa Tour - Part 1(휴양 시설 여행 1)",
            "Spa Tour - Part 2(휴양 시설 여행 2)",
            "Spa Tour - Part 3(휴양 시설 여행 3)",
            "Spa Tour - Part 4(휴양 시설 여행 4)",
            "Spa Tour - Part 5(휴양 시설 여행 5)",
            "Spa Tour - Part 6(휴양 시설 여행 6)",
            "Spa Tour - Part 7(휴양 시설 여행 7)",
            "Wet Job - Part 1(궂은 일 1)",
            "Wet Job - Part 2(궂은 일 2)",
            "Wet Job - Part 3(궂은 일 3)",
            "Wet Job - Part 4(궂은 일 4)",
            "Mentor(멘토)",
            "Wet Job - Part 5(궂은 일 5)",
            "Wet Job - Part 6(궂은 일 6)",
            "The guide(가이드)",
            "The Cult - Part 1(광신도 1)",
            "The Cult - Part 2(광신도 2)",
            "Cargo X - Part 1(화물 X 1)",
            "Cargo X - Part 2(화물 X 2)",
            "Cargo X - Part 3(화물 X 3)",
            "Lend lease - Part 2(무기 대여법 2)",
            "Peacekeeping Mission(평화 유지 임무)",
            "Samples(샘플)",
            "TerraGroup Employee(테라 그룹의 고용자)",
            "Classified technologies(기밀 기술)",
            "Cleaner(청소기)",
            "Speical Equipment(특별 장치)",
            "Our Own Land(우리땅)"
        )
        val mechanic = arrayListOf(
            "Introduction(소개)",
            "Gunsmith - Part 1(총기 개조 1)",
            "Gunsmith - Part 2(총기 개조 2)",
            "Gunsmith - Part 3(총기 개조 3)",
            "Gunsmith - Part 4(총기 개조 4)",
            "Gunsmith - Part 5(총기 개조 5)",
            "Gunsmith - Part 6(총기 개조 6)",
            "Gunsmith - Part 7(총기 개조 7)",
            "Gunsmith - Part 8(총기 개조 8)",
            "Gunsmith - Part 9(총기 개조 9)",
            "Gunsmith - Part 10(총기 개조 10)",
            "Gunsmith - Part 11(총기 개조 11)",
            "Gunsmith - Part 12(총기 개조 12)",
            "Gunsmith - Part 13(총기 개조 13)",
            "Gunsmith - Part 14(총기 개조 14)",
            "Gunsmith - Part 15(총기 개조 15)",
            "Gunsmith - Part 16(총기 개조 16)",
            "Insider(인싸)",
            "Surplus Goods(여분의 물품)",
            "Farming - Part 1(채굴 1)",
            "Farming - Part 2(채굴 2)",
            "Farming - Part 3(채굴 3)",
            "Farming - Part 4(채굴 4)",
            "Bad habit(나쁜 습관)",
            "A Shooter born in Heaven(천국에서 태어난 사격수)",
            "Import(수입)",
            "Fertilizers(비료)",
            "Signal - Part 1(신호 1)",
            "Signal - Part 2(신호 2)",
            "Scout(정찰병)",
            "Signal - Part 3(신호 3)",
            "Signal - Part 4(신호 4)",
            "The Chemistry Closet(화학 밀실)",
            "Psycho Sniper(싸이코 저격수)",
            "Back door(뒷문)",
            "Calibration(눈금)",
            "The Courier(택배 배달부)",
            "Corporate Secrets(영업 비밀)",
            "Energy Crisis(에너지 분쟁)"
        )
        val ragman = arrayListOf(
            "Only Business(비지니스 관계)",
            "Fuel Matter(연료 문제)",
            "Inventory Check(주머니 뒤지기)",
            "Make ULTRA Great Again(ULTRA몰을 다시 위대하게)",
            "Big sale(대량 할인)",
            "Inventory Check(주머니 뒤지기)",
            "The Blood of War - Part 1(전쟁의 피 1)",
            "Dressed to kill(끝내주게 입은)",
            "Gratitude(감사)",
            "Hot delivery(뜨거운 배송)",
            "Scavenger(약탈자)",
            "Sales Night(영업일)",
            "Database - Part 1(데이터베이스 1)",
            "Database - Part 2(데이터 베이스 2)",
            "Mini bus(미니 버스)",
            "Sew it good - Part 1(잘 꿰매다 1)",
            "The Blood of War - Part 2(전쟁의 피 2)",
            "The Blood of War - Part 3(전쟁의 피 3)",
            "Living high is not a crime - Part 2(호화로운 삶은 죄가 아니다 2)",
            "Sew it good - Part 2(잘 꿰메다 2)",
            "The key to success(성공을 위한 열쇠)",
            "No Fuss needed(야단 법석 떨 필요없다)",
            "The Stylish one(간지나는 놈)",
            "Supervisor(상사)",
            "Sew it good - Part 3(잘 꿰메다 3)",
            "Living high is not a crime - Part 1(호화로운 삶은 죄가 아니다 1)",
            "Sew it good - Part 4(잘 꿰매다 4)",
            "Charisma brings success(카리스마가 성공을 부른다)",
            "Textile - Part 1(재질 1)",
            "Textile - Part 2(재질 2)",
            "Long Line(긴 줄)",
            "Booze(술)"
        )
        val jaeger = arrayListOf(
            "Acquaintance(지인)",
            "The survivalist path - Unprotected, but dangerous(생존 주의자의 길 - 보호되지 않은, 하지만 위험한",
            "The survivalist path - Thrifty(생존 주의자의 길 - 절약)",
            "The survivalist path - zhivchik(생존 주의자의 길 - 지브치크 음료수)",
            "The survivalist path - Wounded Beast(생존 주의자의 길 - 상처입은 짐승)",
            "The survivalist path - Tough guy(생존 주의자의 길 - 터프가이",
            "The survivalist path - Cold Blooded(생존 주의자의 길 - 냉혈한)",
            "The survivalist path - Combat Medic(생존 주의자의 길 - 의무병)",
            "The survivalist path - Junkie(생존 주의자의 길 - 약쟁이)",
            "Courtesy Visit(의례적인 방문)",
            "Nostalgia(향수)",
            "Ambulance(구급차)",
            "Huntsman path - Secured perimeter(사냥꾼의 길 - 경계 확보)",
            "\"The Tarkov Shooter\" - Part 1(타르코프 사격수 1)",
            "Reserv(저장고)",
            "Huntsman path - Woods cleaning(사냥꾼의 길 - 숲 청소하기)",
            "Huntsman path - Controller(사냥꾼의 길 - 지배자)",
            "Huntsman path - Fishing place(사냥꾼의 길 - 실내 낚시터)",
            "Huntsman path - Evil watchman(사냥꾼의 길 - 사악한 감시자)",
            "Huntsman path - The Trophy(사냥꾼의 길 - 트로피)",
            "Huntsman path - Justice(사냥꾼의 길 - 정의)",
            "Huntsman path - Sell-out(사냥꾼의 길 - 매진)",
            "Huntsman path - Woods keeper(사냥꾼의 길 - 숲 지킴이)",
            "Huntsman path - Eraser - Part 1(사냥꾼의 길 - 지우개 1)",
            "Huntsman path - Eraser - Part 2(사냥꾼의 길 - 지우개 2)",
            "Huntsman path - Factory Chief(사냥꾼의 길 - 공장의 최고 지위자)",
            "The Huntsman Path - Outcasts(사냥꾼의 길 - 버림받은 이들)",
            "Hunting trip(사냥 여행)",
            "Hunter(사냥꾼)",
            "Huntsman path - Sadist(사냥꾼의 길 - 가학 성애자)",
            "Shady business(음지의 일)",
            "\"The Tarkov Shooter\" - Part 2(타르코프 사격수 2)",
            "\"The Tarkov Shooter\" - Part 3(타르코프 사격수 3)",
            "\"The Tarkov Shooter\" - Part 4(타르코프 사격수 4)",
            "\"The Tarkov Shooter\" - Part 5(타르코프 사격수 5)",
            "\"The Tarkov Shooter\" - Part 6(타르코프 사격수 6)",
            "\"The Tarkov Shooter\" - Part 7(타르코프 사격수 7)",
            "\"The Tarkov Shooter\" - Part 8(타르코프 사격수 8)",
            "Pest control(해충 정리)",
            "Swift One(날쌘 놈)",
            "Huntsman Path - Relentless(사냥꾼의 길 - 가차없는)",
            "The Hermit(은둔자)"
        )

        when (dealer) {
            "prapor" -> {
                for (i in prapor.indices) {
                    list.add(QLRecycler(context, prapor[i]))
                }
            }
            "therapist" -> {
                for (i in therapist.indices) {
                    list.add(QLRecycler(context, therapist[i]))
                }
            }
            "fence" -> {
                for (i in fence.indices) {
                    list.add(QLRecycler(context, fence[i]))
                }
            }
            "skier" -> {
                for (i in skier.indices) {
                    list.add(QLRecycler(context, skier[i]))
                }
            }
            "peacekeeper" -> {
                for (i in peacekeeper.indices) {
                    list.add(QLRecycler(context, peacekeeper[i]))
                }
            }
            "mechanic" -> {
                for (i in mechanic.indices) {
                    list.add(QLRecycler(context, mechanic[i]))
                }
            }
            "ragman" -> {
                for (i in ragman.indices) {
                    list.add(QLRecycler(context, ragman[i]))
                }
            }
            "jaeger" -> {
                for (i in jaeger.indices) {
                    list.add(QLRecycler(context, jaeger[i]))
                }
            }
        }

        val adapter = QLAdapter(list)
        val recyclerView = view.rl_headset_menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    class QLRecycler(val context: Context?, val title: String)

    class QLAdapter(private val items: ArrayList<QLRecycler>) :
        RecyclerView.Adapter<QLAdapter.ViewHolder>() {
        private var previousPosition = -1

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val view = v
            fun bind(item: QLRecycler) {
                view.main_textView.text = item.title
                view.main_imageView.visibility = View.GONE
                view.main_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0F)
                view.onClick {
                    try {
                        item.context?.startActivity(
                            Intent(
                                item.context,
                                QuestActivity::class.java
                            ).putExtra(
                                "quest",
                                "q_${
                                    item.title.split("(")[0].replace("\"", "").replace("\'", "")
                                        .replace("?", "").replace(",", "").replace(" - ", "_")
                                        .replace(" ", "_").replace("-", "_")
                                        .lowercase(Locale.ROOT)
                                }"
                            )
                        )
                    }catch (e: Exception){
                        Toast.makeText(item.context, "해당 퀘스트의 정보가 아직 없습니다.", Toast.LENGTH_SHORT).show()
                        Toast.makeText(item.context, "다음 업데이트를 기다려 주세요.", Toast.LENGTH_SHORT).show()
                    }
                    return@onClick
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView =
                LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
            return ViewHolder(inflatedView)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (position > previousPosition) {
                val ani = AlphaAnimation(0.0f, 2.0f)
                ani.fillAfter = true
                ani.duration = 1000
                holder.itemView.animation = ani
            }

            previousPosition = holder.adapterPosition
            val item = items[position]
            holder.apply {
                bind(item)
                itemView.tag = item
            }
        }
    }
}