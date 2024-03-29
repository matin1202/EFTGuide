package com.matin.eftguide.adapter.main

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.matin.eftguide.*
import org.jetbrains.anko.startActivity
import com.matin.eftguide.base.loadWithWebp
import kotlinx.android.synthetic.main.main_list.view.*
import java.util.*

class MainAdapter(private val items: ArrayList<RecyclerMain>) :
        RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private var previousPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
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

        previousPosition = position
        val item = items[position]
        holder.apply{
            bind(item)
            itemView.tag = item
        }

    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
        fun bind(item: RecyclerMain){
            view.main_textView.text = item.title
            if(item.target != MapActivity::class.java) {
                if (item.target == ExplainActivity::class.java) {
                    loadWithWebp(item.context, view.main_imageView, item.image)
                    view.main_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0F)
                }
                if (item.target == ItemExplainActivity::class.java) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        loadWithWebp(item.context, view.main_imageView, item.image)
                        view.main_textView.setAutoSizeTextTypeUniformWithConfiguration(
                            8,
                            18,
                            2,
                            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                        )
                    }
                } else {
                    loadWithWebp(item.context, view.main_imageView, item.image)
                }
            }
            else{
                view.main_imageView.setImageResource(android.R.color.transparent)
            }
            view.setOnClickListener{
                
                if(item.extra == "shutdown"){
                    val activity = item.context as Activity
                    activity.finish()
                    return@setOnClickListener
                }
                if(item.target == ExplainActivity::class.java){
                    val intent = Intent(item.context, item.target)
                    intent.putExtra("type", item.extra)
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    item.context.startActivity(intent)
                    return@setOnClickListener
                }
                if(item.target == ItemExplainActivity::class.java){
                    Log.d("MA", item.extra)
                    val intent = Intent(item.context, item.target)
                    intent.putExtra("type", item.extra)
                    intent.putExtra("image", item.image)
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    item.context.startActivity(intent)
                    return@setOnClickListener
                }
                if (item.target == MapActivity::class.java){
                    val intent = Intent(item.context, item.target)
                    intent.putExtra("maps", item.extra)
                    intent.putExtra("image", item.image)
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    item.context.startActivity(intent)
                    return@setOnClickListener
                }
                if(item.target == RigActivity::class.java){
                    val intent = Intent (item.context, item.target)
                    intent.putExtra("image", item.image)
                    intent.putExtra("type", item.extra)
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                    item.context.startActivity(intent)
                    return@setOnClickListener
                }
                val intent = Intent(item.context, item.target)
                intent.putExtra("where", item.extra)
                intent.putExtra("title", item.title)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                item.context.startActivity(intent)
            }
        }
    }

}