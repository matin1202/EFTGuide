package com.matin.eftguide

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainList : LinearLayout {
    var imageView: ImageView? = null
    var textView: TextView? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.main_list, this, true)
        imageView = findViewById(R.id.main_imageView)
        textView = findViewById(R.id.main_textView)
    }

    fun setImage(resId: Int) {
        imageView!!.setImageResource(resId)
    }

    fun setText(text: String?) {
        textView!!.text = text
    }
}