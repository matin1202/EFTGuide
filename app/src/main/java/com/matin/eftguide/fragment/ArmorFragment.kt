package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.MenuActivity
import com.matin.eftguide.R
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain

class ArmorFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_armor, container, false)

        val list = arrayListOf<RecyclerMain>()

        list.add(
            RecyclerMain(
                R.drawable.gear,
                "방탄복",
                context!!,
                MenuActivity::class.java,
                "armor vest"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.gear,
                "방탄 조끼",
                context!!,
                MenuActivity::class.java,
                "armored rig"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.gear,
                "조끼",
                context!!,
                MenuActivity::class.java,
                "rig"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.helmet ,
                "헬멧",
                context!!,
                MenuActivity::class.java,
                "helmet"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.helmet ,
                "헬멧 부착물",
                context!!,
                MenuActivity::class.java,
                "helmet attachment"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.helmet,
                "야간 투시경(열화상 투시경)",
                context!!,
                MenuActivity::class.java,
                "night vision"
            )
        )

        list.add(
            RecyclerMain(
                R.drawable.headset ,
                "헤드셋",
                context!!,
                MenuActivity::class.java,
                "headset"
            )
        )

        val adapter = MainAdapter(list)
        val recyclerView: RecyclerView = view.findViewById(R.id.rl_armor_menu)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}