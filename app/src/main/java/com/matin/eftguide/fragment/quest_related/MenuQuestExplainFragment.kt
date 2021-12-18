package com.matin.eftguide.fragment.quest_related

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_quest_explain.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.support.v4.toast

class MenuQuestExplainFragment : Fragment() {
    private val quests: String? by lazy { requireArguments().getString("quest") }

    // 이름, 유형, 목표, 요구 조건, 보상, 실패 패널티, 연계 퀘스트, 팁, 카파퀘 필요 여부, 지도

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_quest_explain, container, false)

        try {
            val resId = resources.getIdentifier(quests, "array", context?.packageName)
            val quest = resources.getStringArray(resId)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                view.tv_quest_name.setAutoSizeTextTypeUniformWithConfiguration(
                    8,
                    24,
                    2,
                    TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                )
            }
            view.tv_quest_name.text = quest[0]
            view.tv_quest_kind.text = quest[1]
            view.tv_quest_object.text = quest[2]
            view.tv_quest_requirement.text = quest[3]
            view.tv_quest_reward.text = quest[4]
            view.tv_quest_penalty.text = quest[5]
            view.tv_quest_relate.text = quest[6]
            view.tv_quest_tip.text = quest[7]
            view.tv_quest_need_for_kappa.text = "Kappa 컨테이너를 위한 필수 퀘스트 : ${quest[8]}"

        }catch (e:Resources.NotFoundException){
            toast("현재 데이터가 없습니다.")
            toast("곧 업데이트 예정이니 기다려주세요.")
        }catch (e: Exception){
            toast("에러가 발생하였습니다. $e")
        }

        return view
    }
}