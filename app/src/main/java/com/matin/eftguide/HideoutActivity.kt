package com.matin.eftguide

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matin.eftguide.fragment.quest_related.MenuDialogueFragment

class HideoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hideout)
        when(intent.getStringExtra("place")){
            "bitcoin" -> {
                
            }
        }
    }
    fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_hideoutFrame, fragment).commit()
    }
}