package com.matin.eftguide.fragment.quest_related

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_quest_maps.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.json.JSONObject
import java.io.FileNotFoundException

class MenuQuestMapFragment : Fragment() {
    private val maps: String? by lazy { requireArguments().getString("quest") }
    private var resID: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        resID = resources.getIdentifier(maps, "array", context?.packageName)
        return QuestMapUI(maps).createView(AnkoContext.create(requireContext(), this))
    }

    class QuestMapUI(private val quest: String?): AnkoComponent<MenuQuestMapFragment>{
        override fun createView(ui: AnkoContext<MenuQuestMapFragment>): View = with(ui) {
            scrollView {
                verticalLayout {
                    try {
                        val mapsList = arrayListOf<String>()
                        val titleList = arrayListOf<String>()
                        var questData = ""
                        try {
                            context.openFileInput("quest_data").bufferedReader().readLines()
                                .forEach {
                                    questData += "\n$it"
                                }
                        }catch(e: FileNotFoundException){
                            toast("파일이 다운로드 되지 않았습니다.")
                        }
                        val json = JSONObject(questData)
                        val questInfo = json.getJSONObject("quest_info")
                        val data = questInfo.getJSONArray(quest!!.replace("q_", ""))
                        for (i in 0 until data.length()) {
                            val iObject = data.getJSONObject(i)
                            titleList.add(iObject.getString("id"))
                            mapsList.add(iObject.getString("link"))
                        }
                        for (i in titleList.indices) {
                            textView {
                                this.text = titleList[i]
                                this.textColor = R.color.text_color
                                this.setTextColor(resources.getColor(R.color.text_color))
                                this.textSize = 24.0f
                                this.gravity = Gravity.CENTER
                                this.padding = 16
                                this.onClick {
                                    if(!mapsList[i].startsWith("http")){
                                        context?.startActivity(
                                            Intent(context, PhotoActivity::class.java).putExtra("id", resources.getIdentifier(mapsList[i], "image", context?.packageName)).addFlags(
                                                Intent.FLAG_ACTIVITY_NEW_TASK))
                                        return@onClick
                                    }
                                    val cm =
                                        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                                    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                                    val isConnected: Boolean =
                                        (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
                                    if (!isConnected) {
                                        toast("인터넷에 연결해주세요...")
                                        return@onClick
                                    } else {
                                        it?.snackbar("이동하시겠습니까?", "이동") {
                                            browse(mapsList[i])
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        toast("파일이 다운로드 되지 않았거나 아직 준비중입니다.")
                        e.printStackTrace()
                    }
                }
            }
        }

    }

}