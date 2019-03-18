package com.training.applicationdevice

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.elhazent.picodiploma.androiddevice.R
import kotlinx.android.synthetic.main.activity_browser.*
import org.jetbrains.anko.sdk27.coroutines.onClick



class BrowserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
btnakseslink.onClick {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkurl))))
}
    }
}
