package com.matin.eftguide

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.matin.eftguide.R

class MenuList(context: Context) : LinearLayout(context) {
    var imageView: ImageView? = null
    var textView: TextView? = null
    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.menu_list, this, true)
        imageView = findViewById(R.id.main_imageView)
        textView = findViewById(R.id.main_textView)
    }

    fun setImage(resId: Int) {
        imageView!!.setImageResource(resId)
    }

    fun setText(text: String?) {
        textView!!.text = text
    }

    init {
        init(context)
    }
}