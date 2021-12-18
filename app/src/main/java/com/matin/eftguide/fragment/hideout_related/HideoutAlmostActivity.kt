package com.matin.eftguide.fragment.hideout_related

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.matin.eftguide.R
import kotlinx.android.synthetic.main.activity_hideout_almost.*

class HideoutAlmostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hideout_almost)
        val toolbar = haa_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val spinner = haa_spiner
        val spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.hideout_list, R.layout.haa_spiner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = spinnerAdapter
    }
}