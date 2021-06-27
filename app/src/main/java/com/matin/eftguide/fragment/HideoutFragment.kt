package com.matin.eftguide.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.HideoutActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.hideout_list.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class HideoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hideout, container, false)

        val names = arrayListOf("화장실", "치료소", "발전기", "작업대", "조리대", "창고", "환기구", "조명", "물 수집기", "보안 시설", "사격장", "정보 시설", "난방 시설", "비트코인 채굴장", "술 제작소", "공기 청정 시설", "스캐브 교환소", "태양광 발전기", "도서관", "크리스마스 트리")
        val list = arrayListOf<HORecycler>()
        for(i in names.indices){
            list.add(HORecycler(context!!, names[i]))
        }

        val adapter = HOAdapter(list)

        val recyclerView: RecyclerView = view.findViewById(R.id.rl_hideout_menu)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        return view
    }

    class HORecycler(val ctx: Context, val name: String)

    class HOAdapter(private val items: ArrayList<HORecycler>): RecyclerView.Adapter<HOAdapter.ViewHolder>(){
        private var previousPosition = -1

        class ViewHolder(v: View): RecyclerView.ViewHolder(v){
            val view = v
            fun bind(item: HORecycler){
                view.hideout_name.text = item.name
                view.hideout_icon.imageResource = R.drawable.abg
                view.onClick {
                    item.ctx.startActivity<HideoutActivity>(
                        "place" to item.name
                    )
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.hideout_list, parent, false)
            return ViewHolder(inflatedView)
        }

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

        override fun getItemCount() = items.size
    }


}