package com.matin.eftguide.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matin.eftguide.R

class BulletPenetrateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bullet_penetrate, container, false)

        return view
    }

    fun penetrateChance(curDur: Int, maxDur: Int, armClass: Int, bulletPen: Int): Double {
        val armPer = (curDur/maxDur) * 100

        val armorClass = armClass * 10
        val stopPwr = (121.0f - 5000 / (45.0f + armPer * 2)) * armorClass / 100
        var penChance = 0.0

        if(stopPwr >= bulletPen){
            penChance = (0.4 * Math.pow((stopPwr - bulletPen - 15.0), 2.0))
        }
        else{
            penChance= bulletPen / (0.9 * stopPwr - bulletPen) + 100
        }
        return penChance
    }

    fun durabilityLoss(bulletPen: Int, bulletDmg: Int, armorMaterial: String, armClass: Int): Double {
        val armorClass = armClass * 10.0
        val durMod = bulletPen / armorClass
        val bulletArmorDmg = bulletDmg / 100.0
        val armorFragility = armFragile(armorMaterial)

        return bulletPen * bulletArmorDmg * armorFragility * durMod
    }

    private fun armFragile(material: String): Double{
        when(material){
            "아라미드(Aramid)" -> return 0.25
            "합성 재료(Combined Materials)" -> return 0.5
            "울트라폴리에틸렌(Ultrahighweight Polyethylene)" -> return 0.45
            "알루미늄(Aluminium)" -> return 0.6
            "강철(Armor Steel)" -> return 0.7
            "유리(Glass)" -> return 0.8
            "세라믹(Ceramic)" -> return 0.8
            "티타늄(Titan)" -> return 0.55
        }
        return 1.0
    }
}