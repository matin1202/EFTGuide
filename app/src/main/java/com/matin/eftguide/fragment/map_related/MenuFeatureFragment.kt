package com.matin.eftguide.fragment.map_related

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matin.eftguide.R

class MenuFeatureFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("map") }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_menu_feature, container, false)
        val tv = view.findViewById<TextView>(R.id.tv_menu_feature)
        val str = resources.getString(resources.getIdentifier("feature_${maps!!.replace("map_", "")}", "string", context!!.packageName))
        tv.text = str
        Log.d("MFF", str)
        return view
    }
}