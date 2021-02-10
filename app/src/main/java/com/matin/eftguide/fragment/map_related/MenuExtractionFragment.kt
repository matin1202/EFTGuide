package com.matin.eftguide.fragment.map_related

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.matin.eftguide.R

class MenuExtractionFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("map") }
    private val resId: Int by lazy { requireArguments().getInt("image") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu_extraction, container, false)

        val buffer = resources.getStringArray(resources.getIdentifier("exit_$maps", "array", context!!.packageName))
        Log.d("MEF", buffer[0])
        val arrayName = buffer[0].split(", ")
        val textView = rootView.findViewById<TextView>(R.id.tv_menu_extraction)
        var text = ""
        val names = arrayListOf(getString(R.string.name), getString(R.string.faction), getString(R.string.available), getString(R.string.use_times), getString(R.string.requirement))
        if(arrayName[0]!="추가예정") {
            for (i in arrayName.indices) {
                val array = resources.getStringArray(
                    resources.getIdentifier(
                        arrayName[i],
                        "array",
                        context!!.packageName
                    )
                )
                var stringBuffer = array[0] + "\n"
                for (j in 1 until array.size) {
                    stringBuffer += " - ${names[j]} : ${array[j]}\n"
                }
                text += stringBuffer + "\n"
            }
        }
        else{
            text = "업데이트 중입니다..."
        }
        textView.text = text
        return rootView
    }
}