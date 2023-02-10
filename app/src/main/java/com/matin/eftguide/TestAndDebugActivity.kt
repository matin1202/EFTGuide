package com.matin.eftguide

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest
import com.matin.eftguide.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test_and_debug.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class TestAndDebugActivity : BaseActivity(), PurchasesUpdatedListener {
    private lateinit var activity: Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_and_debug)
        val billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build()

        activity = this

        bt_test_and_debug.onClick {

            BillingClass(this@TestAndDebugActivity, "test")
            if(integratePurchase(billingClient)) {
                cl_test_and_debug.removeView(av_test_and_debug)
                toast("광고가 성공적으로 제거되었습니다.")
            }
        }

        bt_test_and_debug2.onClick {
            integratePurchase(billingClient)
        }
        val adRequest = AdRequest.Builder().build()
        av_test_and_debug.loadAd(adRequest)

        bt_make_crash.onClick{
            val crash = mutableListOf(1, 2, 3)
            val maker = crash[3]
        }
    }

    override fun onPurchasesUpdated(result: BillingResult, list: MutableList<Purchase>?) {

    }

    fun integratePurchase(billingClient: BillingClient): Boolean {
        /*val billingResult = billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP)
        Log.d("TAD", "start! response Code is ${billingResult.responseCode} And List length is ${billingResult.purchasesList?.size}")
        if (billingResult.purchasesList != null && billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            Log.d("TAD", "BillingResponse is OK!")
            for(purchase in billingResult.purchasesList!!){
                return if(purchase.sku == "test"){
                    Log.d("TAD", "integrated")
                    toast("구매가 확인되었습니다!")
                    true
                } else{
                    Log.d("TAD", "Failed")
                    toast("구매하지 않았습니다!")
                    false
                }
            }
        }*/
        return false
    }

}

