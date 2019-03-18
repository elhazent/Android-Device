package com.training.applicationdevice

import android.os.Bundle
import com.elhazent.picodiploma.androiddevice.R
import com.training.applicationdevice.helper.MyFunction
import kotlinx.android.synthetic.main.activity_call_phone.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : MyFunction() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnaudiomanager.onClick {
            moveClass(AudioManagerActivity::class.java!!)
        }

        btnbrowser.onClick {
            moveClass(BrowserActivity::class.java)
        }
        btncamera.onClick {
            moveClass(CameraActivity::class.java)
        }
        btnemail.onClick {
            moveClass(EmailActivity::class.java)
        }
        btnphone.onClick {
            moveClass(CallPhoneActivity::class.java)
        }
        btnsms.onClick {
            moveClass(SmsActivity::class.java)
        }
        btntts.onClick {
            moveClass(TTSActivity::class.java)
        }
        btnvideo.onClick {
            moveClass(VideoActivity::class.java)
        }
        btnalarm.onClick {
            moveClass(AlarmActivity::class.java)
        }
    }
}
