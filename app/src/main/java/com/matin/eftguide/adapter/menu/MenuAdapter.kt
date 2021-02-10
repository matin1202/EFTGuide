package com.matin.eftguide.adapter.menu

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.menu_list.view.*

class MenuAdapter(private val items: ArrayList<RecyclerMenu>): RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    var previousPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.menu_list, parent, false)
        return ViewHolder(
            inflatedView
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position > previousPosition ){
            val ani = AlphaAnimation(0.0f, 1.0f)
            ani.fillAfter = true
            ani.duration = 1000
            holder.itemView.animation = ani
        }

        previousPosition = position;
        val item = items[position]
        holder.apply{
            bind(item)
            itemView.tag = item
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view = v
        fun bind(item: RecyclerMenu){
            Log.d("MA", "Load on MenuAdapter")
            Glide.with(item.context)
                .load(item.image)
                .placeholder(R.drawable.loadingimage)
                .into(view.main_imageView)

            view.main_textView.text = item.title
            view.setOnClickListener {
                val intent = Intent(item.context, ItemExplainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra("type", "w_${item.title}||${item.extra}weapon")
                    .putExtra("image", item.image)
                item.context.startActivity(intent)
            }
        }
    }
}