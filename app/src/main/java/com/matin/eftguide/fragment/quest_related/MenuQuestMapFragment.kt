package com.matin.eftguide.fragment.quest_related

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_quest_maps.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick

class MenuQuestMapFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("quest") }
    private var resID: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resID = resources.getIdentifier(maps, "array", context?.packageName)
        return QuestMapUI(resID, maps).createView(AnkoContext.create(context!!, this))
    }

    class QuestMapUI(private val resID: Int?, maps: String?): AnkoComponent<MenuQuestMapFragment>{
        override fun createView(ui: AnkoContext<MenuQuestMapFragment>): View = with(ui) {
            verticalLayout {

                val quest = resources.getStringArray(resID!!)
                val map = quest[9].split("||")
                val mapsList = arrayListOf<String>()
                val titleList = arrayListOf<String>()
                for(i in map.indices){
                    titleList.add(map[i].split("(")[0])
                    mapsList.add(map[i].split("(")[1].split(")")[0])
                }
                for(i in titleList.indices){
                    textView {
                        this.text = titleList[i]
                        this.textColor = R.color.text_color
                        this.textSize = 24.0f
                        this.gravity = Gravity.CENTER
                        this.padding = 16
                        this.onClick {
                            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager
                            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                            val isConnected: Boolean = (activeNetwork != null&&activeNetwork.isConnectedOrConnecting)
                            if(!isConnected){
                                toast("인터넷에 연결해주세요...")
                                return@onClick
                            }
                            else{
                                it?.snackbar("이동하시겠습니까?", "이동"){
                                    browse(mapsList[i])
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}