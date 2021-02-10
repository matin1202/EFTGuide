package com.matin.eftguide.fragment.map_related

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.bumptech.glide.Glide
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.main_list.view.main_imageView
import kotlinx.android.synthetic.main.main_list.view.main_textView
import java.util.*

class MenuUsableKeysFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("map") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu_usable_keys, container, false)
        val buffer = resources.getStringArray(resources.getIdentifier("key_$maps", "array", context!!.packageName))[0].split("||")
        val viewInflater = activity!!.layoutInflater
        val linearLayout = rootView.findViewById<LinearLayout>(R.id.ll_menu_explain)
        for(i in buffer.indices){
            val view = viewInflater.inflate(R.layout.main_list, null)
            view.main_textView.text = buffer[i]
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                view.main_textView.setAutoSizeTextTypeUniformWithConfiguration(8, 114, 1, TypedValue.COMPLEX_UNIT_SP)
            }
            else{
                view.main_textView.textSize = 14.0f
            }
            val name = buffer[i].replace("-", "_").replace(" ", "_").substringBeforeLast("(").replace("\n", "").replace(".", "").toLowerCase(Locale.ROOT).replace("(", "").replace(")", "")
            val resId = resources.getIdentifier(name, "drawable", context!!.packageName)
            Log.d("MUKF", name)
            view.main_imageView.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(this).load(resId).into(view.main_imageView)
            linearLayout.addView(view)
        }
        return rootView
    }
}