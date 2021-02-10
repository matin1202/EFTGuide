package com.matin.eftguide.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matin.eftguide.ItemExplainActivity
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.fragment_headset.view.*
import kotlinx.android.synthetic.main.main_list.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick

class HelmetAttachmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_headset, container, false)

        val list = ArrayList<HARecycler>()

        list.add(
            HARecycler(
                context,
                "Ops-core visor",
                R.drawable.visor_ops_core_small
            )
        )

        list.add(
            HARecycler(
                context,
                "Caiman Visor",
                R.drawable.visior_caiman
            )
        )

        list.add(
            HARecycler(
                context,
                "K1S Visor",
                R.drawable.visor_k1s
            )
        )

        list.add(
            HARecycler(
                context,
                "Multi-hit Ops-Core Visor",
                R.drawable.visor_ops_core
            )
        )

        list.add(
            HARecycler(
                context,
                "Kiver Visor",
                R.drawable.visor_kiver
            )
        )

        list.add(
            HARecycler(
                context,
                "EXFIL Visor",
                R.raw.visor_exfil,
                true
            )
        )

        list.add(
            HARecycler(
                context,
                "Zsh-1-2M Visor",
                R.drawable.visor_zsh_1_2m
            )
        )

        list.add(
            HARecycler(
                context,
                "LSHZ-2DTM Visor",
                R.drawable.visor_lshz_2dtm
            )
        )

        list.add(
            HARecycler(
                context,
                "Vulkan-5 Visor",
                R.drawable.vulkan_5
            )
        )

        list.add(
            HARecycler(
                context,
                "Altyn Visor",
                R.drawable.visor_alytn
            )
        )

        list.add(
            HARecycler(
                context,
                "Rys-T Visor",
                R.drawable.visior_rys_t
            )
        )

        list.add(
            HARecycler(
                context,
                "Maska Visor",
                R.raw.visor_maska,
                true
            )
        )

        list.add(
            HARecycler(
                context,
                "Ops-Core Mandible",
                R.drawable.attachment_chop_ops_core
            )
        )

        list.add(
            HARecycler(
                context,
                "Caiman Mandible",
                R.drawable.attachment_chop_caiman
            )
        )

        list.add(
            HARecycler(
                context,
                "Tac-Kek Trooper Mask",
                R.drawable.attachment_trooper
            )
        )

        list.add(
            HARecycler(
                context,
                "Ops-Core Side Armor",
                R.drawable.attachment_ops_core
            )
        )

        list.add(
            HARecycler(
                context,
                "Airframe Ears",
                R.drawable.attachment_airframe
            )
        )

        list.add(
            HARecycler(
                context,
                "EXFIL Ear Cover",
                R.raw.attachment_exfil,
                true
            )
        )

        list.add(
            HARecycler(
                context,
                "Airframe Chops",
                R.drawable.attachment_chop_airframe
            )
        )

        list.add(
            HARecycler(
                context,
                "Caiman Applique",
                R.drawable.attachment_caiman
            )
        )

        list.add(
            HARecycler(
                context,
                "slaap Plate",
                R.drawable.attachment_slaap
            )
        )

        list.add(
            HARecycler(
                context,
                "LSHZ-2DTM Aventail",
                R.drawable.attachment_lshz_2dtm
            )
        )

        list.add(
            HARecycler(
                context,
                "Additional Armor Bastion",
                R.drawable.attachment_bastion
            )
        )

        val adapter = HAAdapter(list)
        val recyclerView = view.rl_headset_menu
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }

    class HARecycler(val context: Context?, val title: String, val image: Int, val isRaw: Boolean = false)

    class HAAdapter(private val items: ArrayList<HARecycler>):
        RecyclerView.Adapter<HAAdapter.ViewHolder>() {
        private var previousPosition = -1

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val view = v
            fun bind(item: HARecycler){
                if(item.isRaw){
                    Glide.with(item.context!!)
                        .asGif()
                        .load(item.image)
                        .into(view.main_imageView)
                }
                else {
                    view.main_imageView.imageResource = item.image
                }
                view.main_textView.text = item.title

                view.onClick{
                    item.context?.startActivity(Intent(item.context, ItemExplainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("type", "h_${item.title}||helmet_attachment").putExtra("image", item.image))
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