package com.matin.eftguide.fragment.quest_related

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_quest_dialogue.view.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException

class MenuDialogueFragment : Fragment() {
    private val quest: String? by lazy { requireArguments().getString("quest") }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_quest_dialogue, container, false)

        var questData = ""

        context!!.openFileInput("quest_data").bufferedReader().readLines().forEach {
            questData += "\n$it"
        }

        try {
            val json = JSONObject(questData)
            val questInfo = json.getJSONObject("dialogue")
            val data = questInfo.getJSONObject(quest!!.replace("q_", ""))
            val startData = data.getString("start")
            val successData = data.getString("success")
            val failData = data.getString("fail")
            val tv_Start = rootView.tv_quest_accept
            val tv_Success = rootView.tv_quest_success
            val tv_Fail = rootView.tv_quest_fail
            if (startData != "") {
                tv_Start.text = "테스크 수락\n\n$startData"
            }
            if (successData != "") {
                tv_Success.text = "테스크 성공\n\n$successData"
            } else {
                tv_Success.visibility = View.GONE
            }
            if (failData != "") {
                tv_Fail.text = "테스크 실패\n\n$failData"
            } else {
                tv_Fail.visibility = View.GONE
            }
            if (startData == "" && successData == "" && failData == "") {
                tv_Start.text = "준비 중입니다."
            }
        }catch(e: Exception){
            e.printStackTrace()
            rootView.tv_quest_accept.text = "준비 중입니다."
        }

        return rootView
    }
}