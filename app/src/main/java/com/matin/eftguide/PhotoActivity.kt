package com.matin.eftguide

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.google.android.gms.ads.AdRequest
import com.matin.eftguide.base.BaseActivity
import com.matin.eftguide.classes.AdLoaderClass
import kotlinx.android.synthetic.main.activity_photo.*
import java.io.File

class PhotoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        if(prefs.getBoolean("adLoad", true)) {
            pa_adView.loadAd(AdLoaderClass().adRequest)
        }

        val resId = intent.getIntExtra("id", R.drawable.abg)
        val url = intent.getStringExtra("url")

        if(url != null){
            Glide.with(this).download(GlideUrl(url)).into(ImageViewTarget(iv_photo))
        }
        else {
            iv_photo.setImage(ImageSource.resource(resId))
        }
    }

    class ImageViewTarget(view: SubsamplingScaleImageView): CustomViewTarget<SubsamplingScaleImageView, File>(view){
        override fun onLoadFailed(errorDrawable: Drawable?) {

        }

        override fun onResourceCleared(placeholder: Drawable?) {

        }

        override fun onResourceReady(resource: File, transition: Transition<in File>?) {
            view.setImage(ImageSource.uri(Uri.fromFile(resource)))
        }

    }
}