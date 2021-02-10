package com.matin.eftguide.adapter.hideout

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.MenuActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.hideout_list.view.*
import org.jetbrains.anko.imageResource

class HideoutAdapter(private val items: ArrayList<RecyclerHideout>) :
    RecyclerView.Adapter<HideoutAdapter.ViewHolder>() {
    private var previousPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.hideout_list, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > previousPosition) {
            val ani = AlphaAnimation(0.0f, 2.0f)
            ani.fillAfter = true
            ani.duration = 1000
            holder.itemView.animation = ani
        }

        previousPosition = position
        val item = items[position]
        holder.apply {
            bind(item)
            itemView.tag = item
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view: View = v
        fun bind(item: RecyclerHideout) {
            view.hideout_icon.imageResource = item.id
            view.hideout_name.text = item.name
            view.setOnClickListener {
                loadPage(item.ctx, item.name)
            }
        }

        private fun loadPage(ctx: Context, dealer: String) {
            ctx.startActivity(
                Intent(ctx, MenuActivity::class.java).putExtra(
                    "where",
                    "dealer_explain"
                ).putExtra("dealer", dealer.split("(")[0])
            )
        }
    }
}