package com.training.applicationdevice

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.media.AudioManager
import android.Manifest.permission
import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.content.Context.AUDIO_SERVICE
import com.elhazent.picodiploma.androiddevice.R
import com.training.applicationdevice.helper.MyFunction
import kotlinx.android.synthetic.main.activity_audio_manager.*
import org.jetbrains.anko.sdk25.coroutines.onClick


class AudioManagerActivity : MyFunction() {
    var manager: AudioManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_manager)
        val manager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(Manifest.permission.RECORD_AUDIO),
                        10)
            }
            return
        }
ring.onClick {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //nougat
        manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
        manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
        manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
        manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
        manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
    }else{
        manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        mytoast("dalam mode normal");
    }
}
        vibrate.onClick {
            manager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE)
        }
    }
}
