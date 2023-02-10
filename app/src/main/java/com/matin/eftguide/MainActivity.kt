package com.matin.eftguide

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.ads.MobileAds
import com.google.firebase.storage.FirebaseStorage
import com.matin.eftguide.adapter.main.MainAdapter
import com.matin.eftguide.adapter.main.RecyclerMain
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import com.matin.eftguide.classes.BackPressCloserHandler
import com.matin.eftguide.data.Datas
import com.matin.eftguide.exceptionHandlers.EFTApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.download_dialog.view.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.math.floor

@Suppress("DEPRECATION")
class MainActivity : BaseActivity() {


    var times = 7

    @SuppressLint("ResourceType")
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


        if (checkInternetConnection()) {
            var datas = ""
            var questData = ""
            val quest_ref = FirebaseStorage.getInstance()
                .getReferenceFromUrl("gs://eft-guide.appspot.com/quest_data.json")
            val data_ref = FirebaseStorage.getInstance()
                .getReferenceFromUrl("gs://eft-guide.appspot.com/output.json")

            try {
                this.openFileInput("quest_data").bufferedReader().readLines().forEach {
                    questData += "\n$it"
                }
                val quest_json = JSONObject(questData)

                val versionRef = FirebaseStorage.getInstance()
                    .getReferenceFromUrl("gs://eft-guide.appspot.com/quest_verion.txt")
                versionRef.getBytes(1024 * 1024).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val version = String(it.result)
                        if (quest_json.getString("version") != version) {
                            quest_ref.metadata.addOnSuccessListener { meta ->
                                AlertDialog.Builder(
                                    this,
                                    android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar
                                )
                                    .setTitle("퀘스트 정보 데이터 업데이트")
                                    .setMessage("${floor(meta.sizeBytes / 1024 * 0.01) * 100}KB의 데이터를 업데이트합니다.")
                                    .setNegativeButton("취소") { _, _ ->
                                        toast("퀘스트 정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                                    }
                                    .setPositiveButton("확인") { _, _ ->
                                        downloadQuestData()
                                    }
                                    .show()
                            }
                        }
                    } else {
                        toast("버전을 불러오는 데 실패하였습니다. ${it.exception}")
                    }
                }
            } catch (e: Exception) {
                quest_ref.metadata.addOnSuccessListener { meta ->
                    AlertDialog.Builder(
                        this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar
                    )
                        .setTitle("퀘스트 정보 데이터 업데이트")
                        .setMessage("${floor(meta.sizeBytes / 10.0) / 100}KB의 데이터를 업데이트합니다.")
                        .setNegativeButton("취소") { _, _ ->
                            toast("퀘스트 정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                        }
                        .setPositiveButton("확인") { _, _ ->
                            downloadQuestData()
                        }
                        .show()
                }
            }
            try {
                this.openFileInput("output").bufferedReader().readLines().forEach {
                    datas += "\n$it"
                }
                val data_json = JSONObject(datas)

                val versionRef = FirebaseStorage.getInstance()
                    .getReferenceFromUrl("gs://eft-guide.appspot.com/data_version.txt")
                versionRef.getBytes(1024 * 1024).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val version = String(it.result)
                        if (data_json.getString("version") != version) {
                            data_ref.metadata.addOnSuccessListener { meta ->
                                AlertDialog.Builder(
                                    this,
                                    android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar
                                )
                                    .setTitle("데이터 업데이트")
                                    .setMessage("${floor(meta.sizeBytes / 1024 * 0.01) * 100}KB의 데이터를 업데이트합니다.")
                                    .setNegativeButton("취소") { _, _ ->
                                        toast("정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                                    }
                                    .setPositiveButton("확인") { _, _ ->
                                        downloadData()
                                    }
                                    .show()
                            }
                        }
                    } else {
                        toast("버전을 불러오는 데 실패하였습니다. ${it.exception}")
                    }
                }
            } catch (e: Exception) {
                data_ref.metadata.addOnSuccessListener { meta ->
                    AlertDialog.Builder(
                        this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar
                    )
                        .setTitle("정보 데이터 업데이트")
                        .setMessage("${floor(meta.sizeBytes / 10.0) / 100}KB의 데이터를 업데이트합니다.")
                        .setNegativeButton("취소") { _, _ ->
                            toast("정보가 제공되지 않거나 오래된 정보일 수 있습니다.")
                        }
                        .setPositiveButton("확인") { _, _ ->
                            downloadData()
                        }
                        .show()
                }
            }
        } else {
            toast("인터넷에 연결되있지 않습니다. 몇몇 기능들이 제한될 수 있습니다.")
        }

        var data = ""
        try {
            this.openFileInput("output").bufferedReader().readLines()
                .forEach {
                    data += "\n$it"
                }
        } catch (e: FileNotFoundException) {
            toast("파일이 다운로드 되지 않았습니다.")
            val inputManager = assets.open("jsons/output.json")
            val inputStreamReader = InputStreamReader(inputManager)
            val reader = BufferedReader(inputStreamReader)

            val buffer = StringBuffer()
            reader.readLines().forEach {
                buffer.append(it + "\n")
            }
            data = buffer.toString()
        } finally {
            val json = JSONObject(data)

            Datas.setBullets(json.getJSONArray("bullets"))
            Datas.setWeapons(json.getJSONArray("weapons"))
        }



        MobileAds.initialize(this)

        if (prefs.getBoolean("adLoad", true)) {
            ma_adView.loadAd(AdLoaderClass().adRequest)
        }

        val toolbar: androidx.appcompat.widget.Toolbar? = tb_main
        toolbar?.setOnClickListener {
            if (times > 0) {
                times--
            } else {
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
                R.drawable.mechanism,
                getString(R.string.system),
                applicationContext,
                MenuActivity::class.java,
                "mechanism"
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

        if (requestCode == 1202) {
            if (resultCode != Activity.RESULT_OK) {
                toast("업데이트가 취소되었습니다.")
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork != null) {
            return true
        }
        return false
    }

    @SuppressLint("SetTextI18n")
    private fun downloadQuestData() {
        try {
            val ref = FirebaseStorage.getInstance()
                .getReferenceFromUrl("gs://eft-guide.appspot.com/quest_data.json")
            ref.getBytes(1024 * 1024)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val os = openFileOutput("quest_data", MODE_PRIVATE)
                        os.write(it.result)
                        os.close()
                    } else {
                        toast("다운로드에 실패하였습니다.")
                    }
                }
            val builder = AlertDialog.Builder(this)
            var isDismissed = false
            val inflate = layoutInflater.inflate(R.layout.download_dialog, null)
            builder.setView(inflate)
                .setNegativeButton("숨기기") { _, _ ->

                }.setOnDismissListener {
                    isDismissed = true
                }
            val dialog = builder.create()
            dialog.show()
            ref.stream.addOnProgressListener {

                inflate.tv_dd_progress.text =
                    "${it.bytesTransferred}/${it.bytesTransferred}  (${floor((it.bytesTransferred / it.totalByteCount) * 100.0)}%"
                inflate.pb_progress.max = it.totalByteCount.toInt()
                inflate.pb_progress.progress = it.totalByteCount.toInt()
            }.addOnSuccessListener {
                inflate.tv_dd_progress.text = "완료"
                Timer().schedule(500) {
                    if (!isDismissed) {
                        dialog.dismiss()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun downloadData() {
        try {
            val ref = FirebaseStorage.getInstance()
                .getReferenceFromUrl("gs://eft-guide.appspot.com/output.json")
            ref.getBytes(1024 * 1024)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val os = openFileOutput("output", MODE_PRIVATE)
                        os.write(it.result)
                        os.close()
                    } else {
                        toast("다운로드에 실패하였습니다.")
                    }
                }
            val builder = AlertDialog.Builder(this)
            var isDismissed = false
            val inflate = layoutInflater.inflate(R.layout.download_dialog, null)
            builder.setView(inflate)
                .setNegativeButton("숨기기") { _, _ ->

                }.setOnDismissListener {
                    isDismissed = true
                }
            val dialog = builder.create()
            dialog.show()
            ref.stream.addOnProgressListener {

                inflate.tv_dd_progress.text =
                    "${it.bytesTransferred}/${it.bytesTransferred}  (${floor((it.bytesTransferred / it.totalByteCount) * 100.0)}%"
                inflate.pb_progress.max = it.totalByteCount.toInt()
                inflate.pb_progress.progress = it.totalByteCount.toInt()
            }.addOnSuccessListener {
                inflate.tv_dd_progress.text = "완료"
                Timer().schedule(500) {
                    if (!isDismissed) {
                        dialog.dismiss()
                    }
                }
                var data = ""
                this.openFileInput("output").bufferedReader().readLines()
                    .forEach {
                        data += "\n$it"
                    }
                val json = JSONObject(data)
                Datas.setBullets(json.getJSONArray("bullets"))
                Datas.setWeapons(json.getJSONArray("weapons"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
