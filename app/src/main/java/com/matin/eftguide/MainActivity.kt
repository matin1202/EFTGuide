package com.matin.eftguide

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.appupdate.testing.FakeAppUpdateManager
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.messaging.FirebaseMessaging
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import com.matin.eftguide.classes.BackPressCloserHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {



    var times = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fakeAppUpdateManager = FakeAppUpdateManager(this)
        fakeAppUpdateManager.let {
            it.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
                if(appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE){
                    fakeAppUpdateManager.startUpdateFlow(
                        appUpdateInfo,
                        this,
                        AppUpdateOptions.defaultOptions(AppUpdateType.FLEXIBLE)
                    )
                }

            }
        }


        val appUpdateManager = AppUpdateManagerFactory.create(this)
        appUpdateManager.let {
            it.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
                if(appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.FLEXIBLE,
                        this,
                        1202
                    )
                }
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        1203
                    )
                }
            }
            
        }

        val listener = InstallStateUpdatedListener {
            if(it.installStatus() == InstallStatus.DOWNLOADED){
                snackbar(main_layout, "업데이트 파일의 설치가 완료되었습니다.", "설치/재시작"){
                    appUpdateManager.completeUpdate()
                }
            }
        }
        appUpdateManager.registerListener(listener)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.w("MA", "Fetching FCM registration token failed", task.exception)
            }

            val token = task.result

            Log.d("MA", "Token is $token")
        })

        MobileAds.initialize(this, getString(R.string.test_ad_code))

        if(prefs.getBoolean("adLoad", true)) {
            ma_adView.loadAd(AdLoaderClass().adRequest)
        }

        val toolbar: androidx.appcompat.widget.Toolbar? = tb_main
        toolbar?.setOnClickListener {
            if(times>0){
                times--
            }
            else{
                times = 7
                startActivity(Intent(this, ExtraActivity::class.java))
            }
        }
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        

        val collapsingToolBar = ctl_main
        collapsingToolBar.title = getString(R.string.title)

        val list = ArrayList<RecyclerMain>()

        list.add(
            RecyclerMain(
                R.drawable.ammo,
                getString(R.string.bullet),
                applicationContext,
                MenuActivity::class.java,
                "ammo"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.weapon,
                getString(R.string.weapon),
                applicationContext,
                MenuActivity::class.java,
                "weapon"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.gear,
                getString(R.string.wearable),
                applicationContext,
                MenuActivity::class.java,
                "gear"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.item,
                getString(R.string.item),
                applicationContext,
                MenuActivity::class.java,
                "item"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.map,
                getString(R.string.map),
                applicationContext,
                MenuActivity::class.java,
                "map"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.mekanism,
                getString(R.string.system),
                applicationContext,
                MenuActivity::class.java,
                "system"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.dealer,
                getString(R.string.dealer),
                applicationContext,
                MenuActivity::class.java,
                "dealer"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.quest,
                getString(R.string.quest),
                applicationContext,
                MenuActivity::class.java,
                "quest"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.hideout,
                getString(R.string.hideout),
                applicationContext,
                MenuActivity::class.java,
                "hideout"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.abg,
                getString(R.string.etc),
                applicationContext,
                InfoActivity::class.java,
                "etc"
            )
        )
        list.add(
            RecyclerMain(
                R.drawable.setting,
                "설정",
                applicationContext,
                SetActivity::class.java,
                "setting"
            )
        )


        val adapter = MainAdapter(list)
        rl_main.adapter = adapter

        rl_main.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        val backPressHandler = BackPressCloserHandler()
        backPressHandler.onBackPress(this)
    }
    

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1202){
            if(resultCode != Activity.RESULT_OK){
                toast("업데이트가 취소되었습니다.")
            }
        }
    }

    
}
