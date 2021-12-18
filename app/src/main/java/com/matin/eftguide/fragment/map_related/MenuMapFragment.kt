package com.matin.eftguide.fragment.map_related

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.R
import org.jetbrains.anko.wrapContent

class MenuMapFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("map") }
    private val resId: Int by lazy { requireArguments().getInt("image") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_map, container, false)
        val imageView = view.findViewById<ImageView>(R.id.iv_map_map_map)
        Glide.with(this).load(resId).thumbnail(0.5f).override(wrapContent).into(imageView)
        imageView.setOnClickListener{
            context?.startActivity(Intent(context, PhotoActivity::class.java).putExtra("id", resId).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        val stringId = resources.getIdentifier(maps, "array", context?.packageName)
        val explainArray = resources.getStringArray(stringId)
        val textView = view.findViewById<TextView>(R.id.tv_menu_map)
        var text = ""
        val buffer = arrayListOf(getString(R.string.name), getString(R.string.playtime), getString(R.string.players), getString(R.string.enermy))
        for(i in buffer.indices){
            text += "${buffer[i]} : ${explainArray[i]}\n"
        }
        textView.text = text
        return view
    }

}