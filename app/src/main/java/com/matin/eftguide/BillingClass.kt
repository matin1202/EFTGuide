package com.matin.eftguide

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.android.billingclient.api.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BillingClass(activity: Activity, whatIsTarget: String): PurchasesUpdatedListener {
    private val tag = "PUL"
    private var act: Activity = activity
    private lateinit var billingClient: BillingClient

    init {
            billingManager(activity, whatIsTarget)
    }

    private fun billingManager(activity: Activity, whatIsTarget: String){
        Log.d(tag, "BillingManager Start!")
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.d(tag, "response is ${billingResult.responseCode}")
                if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
                    val skuList = ArrayList<String>()
                    skuList.add("test")
                    skuList.add("donate1")
                    skuList.add("donate2")
                    skuList.add("donate3")
                    skuList.add("crazy")
                    val params = SkuDetailsParams.newBuilder()
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)
                    GlobalScope.launch {
                        withContext(Dispatchers.IO){
                            billingClient.querySkuDetailsAsync(params.build()) { result, list ->
                                if(result.responseCode == BillingClient.BillingResponseCode.OK && list != null){
                                    for(skuDetail in list){
                                        val sku = skuDetail.sku
                                        if(sku == whatIsTarget){
                                            val flowParams = BillingFlowParams.newBuilder()
                                                .setSkuDetails(skuDetail)
                                                .build()
                                            billingClient.launchBillingFlow(activity, flowParams)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else{
                    Log.d(tag, "Error: responseCode is ${billingResult.responseCode}")
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.d(tag, "결제 요청도중 연결이 끊겼습니다.")
            }
        })
    }

    override fun onPurchasesUpdated(result: BillingResult, purchases: MutableList<Purchase>?) {
        if(result.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                consumePurchase(purchase)
            }
        }
    }

    private fun consumePurchase(purchase: Purchase){
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                billingClient.consumeAsync(consumeParams) { billingResult, _ ->
                    if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
                        Toast.makeText(act, "후원 정말 감사드립니다. 열정과 노력으로 보답하겠습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /*private fun acknowledgePurchase(purchase: Purchase){
        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                billingClient.acknowledgePurchase(acknowledgePurchaseParams)
            }
        }
    } */
}