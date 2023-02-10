package com.matin.eftguide.data

import android.util.Log
import com.matin.eftguide.R
import org.json.JSONArray

object Datas {

    private val bullets: MutableList<Bullet> = mutableListOf()
    private val weapons: MutableList<Weapon> = mutableListOf()

    fun findBullet(name: String): Bullet {
        for (bullet in bullets) {
            if (bullet.name == name) {
                return bullet
            }
        }
        return bullets[0]
    }

    fun getAllBulletImage(): List<Int> {
        val images: MutableList<Int> = mutableListOf()
        for (bullet in bullets) {
            images.add(bullet.image)
        }
        return images
    }


    fun getAllBullet(): List<String> {
        val names: MutableList<String> = mutableListOf()
        for (bullet in bullets) {
            names.add(bullet.name)
        }
        return names
    }

    fun setWeapons(datas: JSONArray) {
        for (i in 0 until datas.length()) {
            val data = datas.getJSONObject(i)
            weapons.add(
                Weapon(
                    data.getString("name"),
                    data.getString("type"),
                    data.getString("weight"),
                    data.getString("sold_by"),
                    data.getString("size"),
                    data.getString("recoil"),
                    data.getString("effective"),
                    data.getString("ergo"),
                    data.getString("firing_md"),
                    data.getString("rpm"),
                    data.getString("moa"),
                    data.getString("range"),
                    data.getString("caliber")
                )
            )
        }
    }

    fun getWeapons() = weapons

    fun findWeapon(name: String): Weapon? {
        for (weapon in weapons) {
            if (weapon.name == name) {
                return weapon
            }
        }
        return null
    }

    fun getAllWeapon(): List<String> {
        val names: MutableList<String> = mutableListOf()
        for (weapon in weapons) {
            names.add(weapon.name)
        }
        return names
    }

    fun findBulletByCaliber(caliber: String): List<Bullet> {
        val result = mutableListOf<Bullet>()
        for (bullet in bullets) {
            if (bullet.caliber == caliber) {
                result.add(bullet)
            }
        }
        return result
    }

    fun getBullets() = bullets

    fun setBullets(datas: JSONArray) {
        for (i in 0 until datas.length()) {
            val data = datas.getJSONObject(i)
            bullets.add(
                Bullet(
                    data.getString("name"),
                    data.getString("dmg"),
                    data.getString("weight"),
                    data.getString("sold_by"),
                    data.getString("speed"),
                    data.getString("pene"),
                    data.getString("a_dmg"),
                    data.getString("frag_chn"),
                    data.getString("acc"),
                    data.getString("recoil"),
                    data.getString("light"),
                    data.getString("heavy"),
                    data.getString("rico_chn"),
                    data.getString("special"),
                    data.getString("caliber")
                )
            )
        }
        val caliber = listOf(
            "12/70",
            "20/70",
            "23x75mm",
            "9x18mm",
            "9x19mm",
            ".357 Magnum",
            "7.62x25mm",
            ".45 ACP",
            "9x21mm",
            "5.7x28mm",
            "4.6x30mm",
            "9x39mm",
            ".366 TKM",
            "5.45x39mm",
            "5.56x45mm",
            "7.62x39mm",
            ".300 Blackout",
            "7.62x51mm",
            "7.62x54mm R",
            "12.7x55mm",
            ".338 Lapua Magnum"
        )
        val images = listOf(
            arrayListOf(
                R.drawable.buck_525_12,
                R.drawable.buck_85_12,
                R.drawable.buck_65_12,
                R.drawable.buck_7_12,
                R.drawable.flechette_12,
                R.drawable.rip_12,
                R.drawable.sf_12,
                R.drawable.grizzly_12,
                R.drawable.csp_12,
                R.drawable.led_12,
                R.drawable.poleva3_12,
                R.drawable.dual_sabot_12,
                R.drawable.ftx_12,
                R.drawable.poleva6_12,
                R.drawable.bmg_12,
                R.drawable.ap_20_12
            ),
            arrayListOf(
                R.drawable.buck_56_20,
                R.drawable.explosive_20,
                R.drawable.buck_62_20,
                R.drawable.buck_75_20,
                R.drawable.buck_73_20,
                R.drawable.devastator_20,
                R.drawable.poleva3_20,
                R.drawable.star_20,
                R.drawable.poleva6u_20,
                R.drawable.flechetta_20,
                R.drawable.elephant_20
            ),
            arrayListOf(
                R.drawable.star_2375,
                R.drawable.shrapnel_25_2375,
                R.drawable.shrapnel_10_2375,
                R.drawable.barricade_23
            ),
            arrayListOf(
                R.drawable.pmb_18,
                R.drawable.pmm_18,
                R.drawable.bzt_gzh_18,
                R.drawable.rg028_gzh_18,
                R.drawable.pst_gzh_18,
                R.drawable.ppt_gzh_18,
                R.drawable.ppe_gzh_18,
                R.drawable.prs_gs_18,
                R.drawable.ps_gs_ppo_18,
                R.drawable.pso_gzh_18,
                R.drawable.p_gzh_18,
                R.drawable.psv_18,
                R.drawable.sp7_gzh_18,
                R.drawable.sp8_gzh_18
            ).reversed(),
            arrayListOf(
                R.drawable.n7n31_19,
                R.drawable.ap_19,
                R.drawable.pst_19,
                R.drawable.gt_19,
                R.drawable.luger_19,
                R.drawable.pso_19,
                R.drawable.quakemaker_19,
                R.drawable.rip_19
            ).reversed(),
            arrayListOf(
                R.drawable.sp_357,
                R.drawable.hp_357,
                R.drawable.jhp_357,
                R.drawable.fmj_357
            ),
            arrayListOf(
                R.drawable.tt_lrnpc,
                R.drawable.tt_lrn,
                R.drawable.tt_fmj43,
                R.drawable.tt_akbs,
                R.drawable.tt_pgl,
                R.drawable.tt_pt_gzh,
                R.drawable.tt_pst_gzh
            ),
            arrayListOf(
                R.drawable.ap_45,
                R.drawable.fmj_45,
                R.drawable.lasermatch_45,
                R.drawable.hydrashok_45,
                R.drawable.rip_45
            ).reversed(),
            arrayListOf(
                R.drawable.sp13_21,
                R.drawable.sp10_21,
                R.drawable.sp11_21,
                R.drawable.sp12_21
            ).reversed(),
            arrayListOf(
                R.drawable.r37f_57,
                R.drawable.ss198lf_57,
                R.drawable.r37x_57,
                R.drawable.ss197sr_57,
                R.drawable.l191_57,
                R.drawable.sb193_57,
                R.drawable.ss190_57
            ),
            arrayListOf(
                R.drawable.ap_46,
                R.drawable.fmj_46,
                R.drawable.subsonic_46,
                R.drawable.action_46
            ).reversed(),
            arrayListOf(
                R.drawable.bp_939,
                R.drawable.spp_939,
                R.drawable.pab9_939,
                R.drawable.sp6_939,
                R.drawable.sp5_939
            ).reversed(),
            arrayListOf(
                R.drawable.ap_366,
                R.drawable.eko_366,
                R.drawable.fmj_366,
                R.drawable.geksa_366
            ).reversed(),
            arrayListOf(
                R.drawable.igolnik_545,
                R.drawable.bs_545,
                R.drawable.bt_545,
                R.drawable.bp_545,
                R.drawable.pp_545,
                R.drawable.ps_545,
                R.drawable.t_545,
                R.drawable.fmj_545,
                R.drawable.us_545,
                R.drawable.prs_545,
                R.drawable.hp_545,
                R.drawable.sp_545
            ).reversed(),
            arrayListOf(
                R.drawable.ssa_ap_556,
                R.drawable.m995_556,
                R.drawable.m855a1_556,
                R.drawable.m856a1_556,
                R.drawable.m855_556,
                R.drawable.fmj_556,
                R.drawable.m856_556,
                R.drawable.mk_318_556,
                R.drawable.mk255_556,
                R.drawable.hp_556,
                R.drawable.warmage_556
            ).reversed(),
            arrayListOf(
                R.drawable.mai_ap_739,
                R.drawable.bp_739,
                R.drawable.ps_739,
                R.drawable.t45m_739,
                R.drawable.us_739,
                R.drawable.hp_739
            ).reversed(),
            arrayListOf(
                R.drawable.whisper_300,
                R.drawable.vmax_300,
                R.drawable.bpz_300,
                R.drawable.m62_300,
                R.drawable.ap_300
            ).reversed(),
            arrayListOf(
                R.drawable.m933_751,
                R.drawable.m61_751,
                R.drawable.m62_751,
                R.drawable.m80_751,
                R.drawable.bpz_fmj_751,
                R.drawable.tpz_sp_751,
                R.drawable.ultra_nosier_751
            ).reversed(),
            arrayListOf(
                R.drawable.r54_bs,
                R.drawable.r54_snb,
                R.drawable.r54_7bt1,
                R.drawable.r54_7n1,
                R.drawable.r54_lps_gzh,
                R.drawable.r54_t46m
            ).reversed(),
            arrayListOf(R.drawable.ps12b_127, R.drawable.ps12_127, R.drawable.ps12a_127).reversed(),
            arrayListOf(
                R.drawable.ap_338,
                R.drawable.fmj_338,
                R.drawable.upz_338,
                R.drawable.tac_x_338
            ).reversed()
        )

        for (i in caliber.indices){
            val bullets = findBulletByCaliber(caliber[i])
            for (j in bullets.indices){
                try {
                    bullets[j].image = images[i][j]
                }catch (e:Exception){
                    Log.e("DATA", i.toString() + ", " + j.toString())
                }
            }
        }


    }
}

data class Bullet(
    val name: String,
    val dmg: String,
    val weight: String,
    val sold_by: String,
    val speed: String,
    val pene: String,
    val a_dmg: String,
    val frag_chn: String,
    var acc: String,
    var recoil: String,
    val light: String,
    val heavy: String,
    val rico_chn: String,
    val special: String,
    val caliber: String,
    var image: Int = R.drawable.abg
)

data class Weapon(
    val name: String, val type: String, val weight: String, val sold_by: String,
    val size: String, val recoil: String, val effective: String, val ergo: String,
    val firing_md: String, val rpm: String, val moa: String, val range: String,
    val caliber: String
)
