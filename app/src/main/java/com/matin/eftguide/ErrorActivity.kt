package com.matin.eftguide

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_error.*
import kotlinx.coroutines.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class ErrorActivity : AppCompatActivity() {

    private val lastActivityIntent by lazy{ intent.getParcelableExtra<Intent>("last_activity") }
    private val errorMessage by lazy{ intent.getStringExtra("error") }
    private val errorIntent by lazy { intent.getStringExtra("last_fragment")}
    private var job: Job? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        job = CoroutineScope(Dispatchers.Default).launch {
            for(i in 4 downTo 1){
                delay(1000)
                runOnUiThread {
                    tv_error_bug.text = "버그가 발생하였습니다.\n ${i}초 뒤 이전 화면으로 돌아갑니다."
                }
            }
            restartActivity()
        }
        job?.start()
        tv_error_error_text.text = errorMessage
        bt_error_return.onClick{
            restartActivity()
        }
    }

    private fun restartActivity(){
        if(job != null && job!!.isActive){
            job!!.cancel()
            job = null
        }
        errorIntent?.let {
            lastActivityIntent.putExtra("where", it)
        }
        startActivity<MainActivity>()
        startActivity(lastActivityIntent)
        finish()
    }
}
