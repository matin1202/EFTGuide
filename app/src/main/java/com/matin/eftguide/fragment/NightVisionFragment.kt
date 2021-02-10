package com.matin.eftguide.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.PhotoActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_headset.view.*
import kotlinx.android.synthetic.main.image_dialog.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick

class NightVisionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headset, container, false)

        val list = ArrayList<NVRecycler>()

        list.add(
            NVRecycler(
            context,
            "PVS-14 Night Vision Monocular",
            R.drawable.pvs_14,
            R.drawable.sight_pvs_14
        )
        )

        list.add(
            NVRecycler(
                context,
                "PNV-10T Night Vision",
                R.drawable.pnv_10r ,
                R.drawable.sight_pnv_10t
            )
        )

        list.add(
            NVRecycler(
                context,
                "N-15 Night Vision",
                R.drawable.n_15 ,
                R.drawable.sight_n_15
            )
        )

        list.add(
            NVRecycler(
                context,
                "GPNVG-18 Night Vision",
                R.drawable.gpnvg_18 ,
                R.drawable.sight_gpnvg
            )
        )

        list.add(
            NVRecycler(
                context,
                "T-7 Thermal Goggle",
                R.drawable.t_7 ,
                R.drawable.sight_t_7
            )
        )

        val adapter = NVAdapter(list)
        val recyclerView = view.rl_headset_menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }

    class NVRecycler(val context: Context?, val title: String, val image: Int, val vision: Int)

    class NVAdapter(private val items: ArrayList<NVRecycler>):
        RecyclerView.Adapter<NVAdapter.ViewHolder>() {
        private var previousPosition = -1

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val view = v
            fun bind(item: NVRecycler){
                view.main_imageView.imageResource = item.image
                view.main_textView.text = item.title

                view.onClick{
                    val dialog = AlertDialog.Builder(item.context, R.style.MyDialogTheme)
                    val inflater = item.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val dialogView = inflater.inflate(R.layout.image_dialog, null)

                    dialogView.iv_dialog.imageResource = item.vision

                    dialogView.iv_dialog.onClick{
                        item.context.startActivity(Intent(item.context, PhotoActivity::class.java).putExtra("id", item.vision).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    }

                    dialog.setView(dialogView)
                        .setPositiveButton("닫기", null)
                        .setTitle(item.title)
                        .show()
                    return@onClick
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.main_list, parent, false)
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
    }
}