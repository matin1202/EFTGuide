package com.matin.eftguide

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
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
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import kotlin.math.floor

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {



    var times = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val appUpdateManager = AppUpdateManagerFactory.create(this)
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
        appUpdateManager.registerListener(listener)*/

        if(checkInternetConnection()){
            var questData = ""
            try{
            this.openFileInput("quest_data").bufferedReader().readLines().forEach{
                questData += "\n$it"
            }
            Log.d("MA", "Quest Data: $questData")
            val json = JSONObject(questData)
            val ref = FirebaseStorage.getInstance().getReferenceFromUrl("gs://eft-guide.appspot.com/data_version.txt")
            ref.getBytes(1024*1024).addOnCompleteListener {
                if(it.isSuccessful){
                    val version = String(it.result)
                    if(json.getString("version")!=version){
                        AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)
                            .setTitle("퀘스트 정보 데이터 다운로드")
                            .setMessage("1MB이하의 데이터를 다운로드합니다.")
                            .setNegativeButton("취소"){ _, _ ->
                                toast("퀘스트 정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                            }
                            .setPositiveButton("확인"){ _, _ ->
                                downloadQuestData()
                            }
                            .show()
                    }
                }
                else{
                    toast("버전을 불러오는 데 실패하였습니다. ${it.exception}")
                }
            }
            }catch(e: Exception){
                AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)
                    .setTitle("퀘스트 정보 데이터 다운로드")
                    .setMessage("1MB이하의 데이터를 다운로드합니다.")
                    .setNegativeButton("취소"){ _, _ ->
                        toast("퀘스트 정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                    }
                    .setPositiveButton("확인"){ _, _ ->
                        downloadQuestData()
                    }
                    .show()
            }
        }
        else{
            toast("인터넷에 연결되있지 않습니다. 몇몇 기능들이 제한될 수 있습니다.")
        }

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

    private fun checkInternetConnection(): Boolean{
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if(activeNetwork != null){
            return true
        }
        return false
    }

    private fun downloadQuestData(){
        try {
            val ref = FirebaseStorage.getInstance()
                .getReferenceFromUrl("gs://eft-guide.appspot.com/quest_data.json")
            ref.getBytes(1024 * 1024).addOnCompleteListener {
                if (it.isSuccessful) {
                    val os = openFileOutput("quest_data", MODE_PRIVATE)
                    os.write(it.result)
                    os.close()
                    toast("다운로드에 성공했습니다.")
                    toast("다운로드 크기 : ${floor(it.result.size.toDouble()/1024*10) /10}KB")
                }
                else{
                    toast("다운로드에 실패하였습니다.")
                }
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

}
