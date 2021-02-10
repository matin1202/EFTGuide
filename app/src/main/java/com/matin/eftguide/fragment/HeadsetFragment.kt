package com.matin.eftguide.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_headset.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick

class HeadsetFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headset, container, false)

        val list = ArrayList<HFRecycler>()

        list.add(
            HFRecycler(
                context,
                "ComTac-2",
                R.drawable.comtac
            )
        )

        list.add(
            HFRecycler(
                context,
                "Razor",
                R.drawable.razor
            )
        )

        list.add(
            HFRecycler(
                context,
                "XCEL 500BT",
                R.drawable.xcel
            )
        )

        list.add(
            HFRecycler(
                context,
                "sordin",
                R.drawable.sordin
            )
        )

        list.add(
            HFRecycler(
                context,
                "Ops-Core RAC",
                R.drawable.fast_rac
            )
        )

        list.add(
            HFRecycler(
                context,
                "GSSh-01",
                R.drawable.gssh
            )
        )

        list.add(
            HFRecycler(
                context,
                "Tactical Sport",
                R.drawable.tactical
            )
        )

        val adapter = HFAdapter(list)
        val recyclerView = view.rl_headset_menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }

    class HFRecycler(val context: Context?, val title: String, val image: Int)

    class HFAdapter(private val items: ArrayList<HFRecycler>):
        RecyclerView.Adapter<HFAdapter.ViewHolder>() {
        private var previousPosition = -1

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val view = v
            fun bind(item: HFRecycler){
                view.main_imageView.imageResource = item.image
                view.main_textView.text = item.title

                view.onClick{
                    item.context?.startActivity(Intent(item.context, ItemExplainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("type", "h_${item.title}||headset").putExtra("image", item.image))
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