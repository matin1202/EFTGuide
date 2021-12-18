package com.matin.eftguide.fragment.hideout_related

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_hideout_almost.*
import kotlinx.android.synthetic.main.hideout_table.view.*
import java.lang.Exception
import android.util.Log
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.HideoutActivity
import com.matin.eftguide.fragment.HideoutFragment
import kotlinx.android.synthetic.main.hideout_list.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class HideoutAlmostFragment : Fragment() {

    val data by lazy { requireArguments().getString("where") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val view = inflater.inflate(R.layout.fragment_hideout_almost, container, false)
            val resId = resources.getIdentifier("h_$data", "array", requireContext().packageName)
            val array = resources.getStringArray(resId)
            var lvl = 1
            val list = arrayListOf<HAORecycler>()
            Log.d("HAF", array[2])
            for(i in 1 until array.size step 3) {
                list.add(HAORecycler(lvl.toString(), array[i], array[i+1], array[i+2]))
                lvl ++
            }
            val adapter = HAOAdapter(list)

            val recyclerView: RecyclerView = view.findViewById(R.id.rv_hideout_almost)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context!!)
        return view
    }

    class HAORecycler(val lvl: String, val requirement: String, val func: String, val construction: String)

    class HAOAdapter(private val items: ArrayList<HAORecycler>): RecyclerView.Adapter<HAOAdapter.ViewHolder>(){
        private var previousPosition = -1

        class ViewHolder(v: View): RecyclerView.ViewHolder(v){
            val view = v
            fun bind(item: HAORecycler){
                view.tv_hideout_lvl.text = item.lvl
                view.tv_hideout_requirements.text = item.requirement
                view.tv_hideout_function.text = item.func
                view.tv_hideout_construction_time.text = item.construction
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.hideout_table, parent, false)
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