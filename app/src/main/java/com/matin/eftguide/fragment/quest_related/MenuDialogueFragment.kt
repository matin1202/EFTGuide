package com.matin.eftguide.fragment.quest_related

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_quest_dialogue.view.*

class MenuDialogueFragment : Fragment() {
    private val quests: String? by lazy { requireArguments().getString("quest") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_quest_dialogue, container, false)

        rootView.tv_quest_accept.text = "준비중입니다."

        return rootView
    }
}