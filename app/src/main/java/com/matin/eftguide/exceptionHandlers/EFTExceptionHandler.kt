package com.matin.eftguide.exceptionHandlers

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Process
import com.google.firebase.crashlytics.FirebaseCrashlytics
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.matin.eftguide.ErrorActivity
import com.matin.eftguide.fragment.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

class EFTExceptionHandler(application: Application, private val defaultExceptionHandler: Thread.UncaughtExceptionHandler): Thread.UncaughtExceptionHandler {
    private var lastActivity = arrayListOf<Activity?>()
    private var lastActivityFragment:Fragment?  = null
    private var activityCount = 0

    init {
        application.registerActivityLifecycleCallbacks(
            object : MyActivityLifecyclerCallbacks(){
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if(isErrorActivity(activity)){
                        return
                    }
                    activityCount++
                    lastActivity.add(activity)
                    val fragmentManager = (activity as FragmentActivity).supportFragmentManager
                    for(fragment in fragmentManager.fragments){
                        if(fragment.isVisible){
                            lastActivityFragment = fragment
                        }
                    }
                }

                override fun onActivityStopped(activity: Activity){
                    if(isErrorActivity(activity)){
                        return
                    }
                    activityCount --
                    if(activityCount<0){
                        lastActivity[lastActivity.size-1] = null
                    }
                }
            }
        )
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        Log.e("EH", "Exception Occurred.")
        FirebaseCrashlytics.getInstance().setCustomKey("error", "$throwable")
        lastActivity[lastActivity.size-2]?.run {
            val stringWriter = StringWriter()
            throwable.printStackTrace(PrintWriter(stringWriter))

            startErrorActivity(this, stringWriter.toString(), lastActivityFragment)
        } ?: defaultExceptionHandler.uncaughtException(thread, throwable)

        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }

    private fun startErrorActivity(activity: Activity, errorText: String, lastFragment: Fragment?) = activity.run {
        val errorActivityIntent = Intent(this, ErrorActivity::class.java)
            .apply {
                putExtra("last_activity", intent)
                putExtra("error", errorText)
                putExtra("last_fragment", whichFragment(lastFragment))
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        startActivity(errorActivityIntent)
        finish()
    }

    private fun isErrorActivity(activity: Activity) = activity is ErrorActivity

    private fun whichFragment(fragment: Fragment?): String?{
        return when(fragment) {
            is AmmoFragment -> "ammo"
            is WeaponFragment -> "weapon"
            is HealFragment -> "heal"
            is ProvisionFragment -> "provision"
            is MapFragment -> "map"
            is HeadsetFragment -> "headset"
            is DealerFragment -> "dealer"
            is DealerExplainFragment -> "dealer_explain"
            is ArmorFragment -> "gear"
            is HelmetFragment -> "helmet"
            is ArmorVestFragment -> "armor vest"
            is ArmoredRigFragment -> "armored rig"
            is HelmetAttachmentFragment -> "helmet attachment"
            is NightVisionFragment -> "night vision"
            is QuestFragment -> "quest"
            is MechanismFragment -> "mechanism"
            is BulletPenetrateFragment -> "penetrate chance"
            else -> null
        }
    }
}