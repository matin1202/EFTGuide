package com.matin.eftguide.adapter.ammo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ExplainActivity
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.R
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain
import com.matin.eftguide.adapter.menu.MenuAdapter
import com.matin.eftguide.adapter.menu.RecyclerMenu
import com.matin.eftguide.base.loadWithWebp
import kotlinx.android.synthetic.main.ammo_list.view.*
import kotlinx.android.synthetic.main.main_list.view.*

class AmmoAdapter(private val items: ArrayList<RecyclerAmmo>):
    RecyclerView.Adapter<AmmoAdapter.ViewHolder>() {

    private var previousPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.ammo_list, parent, false)
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

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        private var view: View = v
        fun bind(item: RecyclerAmmo){
            
            view.ammo_textView?.text = item.title
            view.ammo_expansion.setImageResource(R.drawable.ic_baseline_expand_more_24)
            loadWithWebp(item.context, view.ammo_imageView, item.image)
            if(view.ammo_menu.visibility == View.VISIBLE){
                Glide.with(item.context)
                    .load(R.drawable.ic_baseline_expand_less_24)
                    .into(view.ammo_expansion)
            }
            else{
                Glide.with(item.context)
                    .load(R.drawable.ic_baseline_expand_more_24)
                    .into(view.ammo_expansion)
            }
            view.setOnClickListener {v ->
                if(v.ammo_menu.visibility == View.GONE){
                    v.ammo_menu.visibility = View.VISIBLE
                    Glide.with(item.context)
                        .load(R.drawable.ic_baseline_expand_less_24)
                        .into(view.ammo_expansion)
                }
                else{
                    v.ammo_menu.visibility = View.GONE
                    Glide.with(item.context)
                        .load(R.drawable.ic_baseline_expand_more_24)
                        .into(view.ammo_expansion)
                }
            }
            val ammo = item.ammo
            val ammoImages = item.ammoImages
            val list = ArrayList<RecyclerMenu>()
            val list2 = ArrayList<RecyclerMain>()
            loop@ for(i in 0 until ammo.size){
                when (item.type) {
                    "weapon" -> {
                        if(item.title == "Melee Weapon"){
                            list.add(RecyclerMenu(ammoImages[i], ammo[i], item.context, "melee"))
                            continue@loop
                        }
                        if(item.title == "Throwable Weapon"){
                            list.add(RecyclerMenu(ammoImages[i], ammo[i], item.context, "throwable"))
                            continue@loop
                        }
                        list.add(RecyclerMenu(ammoImages[i], ammo[i], item.context))
                    }
                    "heal" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ItemExplainActivity::class.java, "h_${ammo[i]}||${item.type}"))
                        continue@loop
                    }
                    "stimulator" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ItemExplainActivity::class.java, "h_${ammo[i]}||${item.type}"))
                        continue@loop
                    }
                    "provision" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ItemExplainActivity::class.java, "p_${ammo[i]}||${item.type}"))
                        continue@loop
                    }
                    "ammo" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ExplainActivity::class.java, "a${item.title}_${ammo[i]}"))
                        continue@loop
                    }
                    "container" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ItemExplainActivity::class.java, "c_${ammo[i]}||${item.type}"))
                        continue@loop
                    }
                    "secure" -> {
                        list2.add(RecyclerMain(ammoImages[i], ammo[i], item.context, ItemExplainActivity::class.java, "c_${ammo[i]}||${item.type}"))
                        continue@loop
                    }
                }
            }

            if(item.type == "weapon") {
                view.ammo_menu.adapter = MenuAdapter(list)
            }
            else{
                view.ammo_menu.adapter = MainAdapter(list2)
            }
            view.ammo_menu.layoutManager = LinearLayoutManager(item.context)
            view.ammo_menu.addItemDecoration(DividerItemDecoration(item.context, DividerItemDecoration.VERTICAL))
        }
    }
}