package com.matin.eftguide.classes

import com.google.android.gms.ads.AdRequest

public class AdLoaderClass {
    public val adRequest: AdRequest by lazy { AdRequest.Builder().build() }
}